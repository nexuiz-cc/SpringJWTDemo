

# 自分で作ったJava(springboot)  サンプル

## Github ページ

https://github.com/nexuiz-cc/SpringJWTDemo/tree/main/mall-learning/mall-tiny-04

## 使った技術：

| 技術           | バージョン | 説明                                                         |
| -------------- | ---------- | ------------------------------------------------------------ |
| SpringBoot     | 2.7.0      | Java言語の環境下で動作するWebアプリケーションの開発をサポートするフレームワークです。 |
| SpringSecurity | 5.7.1      | Spring ベースのアプリケーションを保護するためのフレームワークです。認証、認可、一般的な攻撃に対する保護を提供します。 |
| MyBatis        | 3.5.9      | Javaを使用してデータベース操作を行うフレームワークです。プログラム上で扱われるデータ集合をデータベースの領域に対応付け、相互に変換することで永続的に保管し続ける仕組みを提供します。 |
| Swagger-UI     | 3.0.0      | APIのドキュメントを表示するためのオープンソースのツールです。OpenAPI Specificationで記述されたAPI定義をインタラクティブに表示・検証することができます。 |



## 機能：

１、ユーザー管理

　　・ユーザー登録

　　・ユーザーログイン

２．商品管理

　　・商品検索

　　・全ての商品を取得

　　・指定された商品数の更新

　　・指定された商品の削除



## ポイントや難しかったところ：

１．ユーザーログイン機能を開発の際：管理員ユーザーに各権限を与えます。

```Java
  private void init() {
    adminUserDetailsList.add(AdminUserDetails.builder()
      .username("admin")
      .password(passwordEncoder.encode("123456"))
      .authorityList(CollUtil.toList("brand:create", "brand:update", "brand:delete", "brand:list", "brand:listAll","product"))
      .build());
      ...
}     
```

２．全ての商品を取得APIはproductの権限がないとアクセス出来ない

```Java
 @ApiOperation("メニュー項目を検索")
  @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('product')")
  @ResponseBody
  public CommonResult<List<Seafood>> getOneSeafood(@PathVariable("name") String name) {
    return CommonResult.success(service.selectOneSeafood(name));
  }
```

3.SpringSecurityに関する設置

```
  SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    ...
    // 権限系のホワイトリストの設置
    for (String url : ignoreUrlsConfig.getUrls()) {
      registry.antMatchers(url).permitAll();
    }
    //クロスドメインリクエストのOPTIONSリクエストを 許可する
    registry.antMatchers(HttpMethod.OPTIONS)
      .permitAll();
    httpSecurity.csrf()
      .disable()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .anyRequest()
      .authenticated();
    httpSecurity.headers().cacheControl();
    // JWT Filterの追加
    httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    //　権限を未承認、ユーザーが未ログインのメッセージ
    httpSecurity.exceptionHandling()
      .accessDeniedHandler(restfulAccessDeniedHandler)
      .authenticationEntryPoint(restAuthenticationEntryPoint);
    return httpSecurity.build();
  }
```

4.　Swaggerの設置

```
@Configuration
public class Swagger2Config {
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.macro.mall.tiny.controller"))
      .paths(PathSelectors.any())
      .build()
      //ログイン認証の追加
      .securitySchemes(securitySchemes())
      .securityContexts(securityContexts());
  }
  ...
 }
```

5.JwtTokenに関して

```Java
public class JwtTokenUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
  private static final String CLAIM_KEY_USERNAME = "sub";
  private static final String CLAIM_KEY_CREATED = "created";
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private Long expiration;

 /**
  * インプットに基づく、アルゴリズム：ＨＳ512を使用し、 トークンを生成する
  */
  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
      .setClaims(claims)
      .setExpiration(generateExpirationDate())
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();
  }

  /**
   * インプットのトークンでユーザー情報を取得
   */
  private Claims getClaimsFromToken(String token) {
    Claims claims = null;
    try {
      claims = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
    } catch (Exception e) {
      LOGGER.info("JWT格式验证失败:{}", token);
    }
    return claims;
  }

  /**
   * トークンの有効期限を生成
   */
  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + expiration * 1000);
  }
  ……
}
```

