package com.practice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // 공통 에러 정의
    INVALID_VALUE     (400, "F0001", "Invalid Input Value"    ),
    INTERNAL_SERVER_ERROR   (500, "F0004", "Server Error"           ),
    INVALID_TYPE_VALUE      (400, "F0005", "Invalid Type Value"     ),
    HANDLE_ACCESS_DENIED    (403, "F0006", "Access is Denied"       ),
    METHOD_NOT_ALLOWED      (405, "F0007", "Method not allowed"    ),

    // 각업무 영역별 에러코드 입력 값에 대한 검증
    // SAMPLE
    NO_SPECIAL_CHARACTERS  (400, ErrorCode.INVALID_VALUE.getCode(), "Special characters cannot be entered"    ),

    // Business Exception
    TEST_BIZ_EXCEPTION  (400, "B0001", "Biz Exception Error"    ),
    USER_NOT_FOUND_EXCEPTION  (400, "B0002", "User does not exist"    );

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
