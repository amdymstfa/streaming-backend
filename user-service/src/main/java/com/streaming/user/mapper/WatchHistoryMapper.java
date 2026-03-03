package com.streaming.user.mapper;

import com.streaming.user.dto.WatchHistoryResponse;
import com.streaming.user.entity.WatchHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WatchHistoryMapper {
    WatchHistoryResponse toResponse(WatchHistory watchHistory);
}
