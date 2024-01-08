package com.zerobase.mission.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    WRONG_PASSWORD(HttpStatus.BAD_REQUEST.value(), "잘못된 비밀번호 입니다."),
    ALREADY_REGISTERED_USER(HttpStatus.BAD_REQUEST.value(), "이미 가입된 회원입니다."),
    MANAGER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "매니저를 찾을 수 없습니다."),
    CUSTOMER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "고객을 찾을 수 없습니다."),
    EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "email을 찾을 수 없습니다."),

    ALREADY_EXISTED_STORE(HttpStatus.BAD_REQUEST.value(), "이미 등록된 매장입니다."),
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "매장을 찾을 수 없습니다."),
    MANGER_STORE_NOT_MATCHED(HttpStatus.BAD_REQUEST.value(), "해당 관리자의 매장이 아닙니다."),

    REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "리뷰를 찾을 수 없습니다."),
    CUSTOMER_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "정상적인 사용자가 아닙니다."),
    REVIEW_NOT_ALLOWED(HttpStatus.BAD_REQUEST.value(), "리뷰를 작성할 수 있는 상태가 아닙니다."),
    ALREADY_EXISTED_REVIEW(HttpStatus.BAD_REQUEST.value(), "이미 등록된 리뷰입니다."),
    NOT_IN_RANGE_SCORE(HttpStatus.BAD_REQUEST.value(), "올바른 별점이 아닙니다."),
    TOO_LONG_COMMENT(HttpStatus.BAD_REQUEST.value(), "200자 이내에서 코멘트를 작성해주세요."),


    ALREADY_RESERVED(HttpStatus.BAD_REQUEST.value(), "다른 손님께서 예약한 시간입니다."),
    RESERVATION_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "예약을 찾을 수 없습니다."),
    RESERVATION_STATUS_EQUAL(HttpStatus.BAD_REQUEST.value(), "예약 상태가 동일합니다."),
    RESERVATION_STATUS_ERROR(HttpStatus.BAD_REQUEST.value(), "예약이 승인되지 않았습니다."),
    RESERVATION_NOT_VALID(HttpStatus.BAD_REQUEST.value(), "예약 시간이 지났습니다."),
    RESERVATION_CHECK_10_MINUTES(HttpStatus.BAD_REQUEST.value(), "10분전에 도착하지 못했습니다.");


    private final int statusCode;
    private final String description;
}
