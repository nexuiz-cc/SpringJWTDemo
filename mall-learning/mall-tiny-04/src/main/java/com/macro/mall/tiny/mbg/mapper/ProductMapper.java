package com.macro.mall.tiny.mbg.mapper;
import java.util.List;
import com.macro.mall.tiny.mbg.model.Seafood;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProductMapper {
  List<Seafood> selectAll(Seafood seafood);

  List<Seafood> selectByName(String name);

  int updateOneSeafood(Seafood seafood);
  @Select("SELECT count FROM seafood WHERE id = #{id}")
  int getCountById(int id);

  @Update("UPDATE seafood SET count = count + 1,price = count* unitPrice WHERE id = #{id}")
  void increase(int id);
  @Update("UPDATE seafood SET count = count - 1,price = count* unitPrice WHERE id = #{id}")
  void decrease(int id);
  @Select("SELECT price FROM seafood WHERE id = #{id}")
  int getPriceById(int id);
  @Delete("DELETE FROM seafood WHERE id = #{id}")
  void deleteSeafoodById(int id);
}
