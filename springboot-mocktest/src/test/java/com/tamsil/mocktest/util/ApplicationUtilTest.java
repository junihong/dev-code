package com.tamsil.mocktest.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationUtilTest {

    @DisplayName("mockStatic 테스트 - 매서드 매개변수가 없는 경우")
    @Test
    void mockStaticTest_NoArgs() {
        assertThat(ApplicationUtil.getName()).isEqualTo("tamsil");

        try (MockedStatic<ApplicationUtil> applicationUtilMockedStatic = Mockito.mockStatic(ApplicationUtil.class)) {
            applicationUtilMockedStatic.when(ApplicationUtil::getProfile).thenReturn("default");
            assertThat(ApplicationUtil.getProfile()).isEqualTo("default");
        }

        assertThat(ApplicationUtil.getProfile()).isEqualTo("default");
    }

    @DisplayName("mockStatic 테스트 - 메서드 매개변수가 있는 경우")
    @Test
    void mockStaticTest_WithArgs() {
        String studentName = "john";
        String teacherName = "david";
        assertThat(ApplicationUtil.getReservationId(studentName, teacherName)).isEqualTo(String.join("_", studentName, teacherName));

        try (MockedStatic<ApplicationUtil> utilMockedStatic = Mockito.mockStatic(ApplicationUtil.class)) {
            utilMockedStatic.when(() -> ApplicationUtil.getReservationId(studentName, teacherName))
                    .thenReturn(String.join("_", studentName, teacherName));

            assertThat(ApplicationUtil.getReservationId(studentName, teacherName)).isEqualTo(String.join("_", studentName, teacherName));
        }

        assertThat(ApplicationUtil.getReservationId(studentName, teacherName)).isEqualTo(String.join("_", studentName, teacherName));
    }
}