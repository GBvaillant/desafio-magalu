package com.example.magalums.dto;

import com.example.magalums.entity.Channel;

public record ChannelDto(Long id, String name) {
    public static ChannelDto fromEntity(Channel channel) {
        return new ChannelDto(
                channel.getChannel_id(),
                channel.getDescription()
        );
    }
}
