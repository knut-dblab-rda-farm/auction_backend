package org.dblab.auction_backend.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    public String getTime();
}
