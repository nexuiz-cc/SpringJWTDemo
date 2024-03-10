

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

3.
