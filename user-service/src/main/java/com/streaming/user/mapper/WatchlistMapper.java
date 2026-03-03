package com.streaming.user.mapper;

import com.streaming.user.dto.WatchlistResponse;
import com.streaming.user.entity.Watchlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WatchlistMapper {
    WatchlistResponse toResponse(Watchlist watchlist);
}
