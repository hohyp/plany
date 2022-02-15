package com.toy.plany.dto.response.event;

import com.toy.plany.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleByUserResponse {
    private Long userId;
    private String userName;
    private String color;
    private String fontColor;
    private List<EventInfoResponse> eventInfoResponseList;

    @Builder
    public ScheduleByUserResponse(Long userId, String userName, String color, String fontColor, List<EventInfoResponse> eventInfoResponseList) {
        this.userId = userId;
        this.userName = userName;
        this.color = color;
        this.fontColor = fontColor;
        this.eventInfoResponseList = eventInfoResponseList;
    }

    static public ScheduleByUserResponse from(User user, List<EventInfoResponse> eventInfoResponseList){
        return ScheduleByUserResponse.builder()
                .userId(user.getId())
                .userName(user.getName())
                .color(user.getColor().getColor().getCode())
                .fontColor(user.getColor().getFontColor().getCode())
                .eventInfoResponseList(eventInfoResponseList)
                .build();
    }
}
