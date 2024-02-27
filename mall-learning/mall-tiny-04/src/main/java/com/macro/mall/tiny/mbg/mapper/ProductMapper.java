package com.macro.mall.tiny.mbg.mapper;
import java.util.List;
import com.macro.mall.tiny.mbg.model.Seafood;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
  List<Seafood> selectAll(Seafood seafood);

  List<Seafood> selectByName(String name);
}
