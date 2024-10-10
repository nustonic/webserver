package gov.la.webserver.user.scheduler;

import gov.la.webserver.user.dto.UserChangeLogDTO;
import gov.la.webserver.user.service.UserChangeLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@EnableScheduling
@Component
@RequiredArgsConstructor
public class UserChangeLogScheduler {
    private final UserChangeLogService userChangeLogService;

    @Scheduled(fixedRate = 60 * 1000)
    public void clearChangeLogs() {
        log.info("call clearChangeLog");
        final Boolean isResult = userChangeLogService.clearAllUserLogs();

        log.info("clear Logs completed!! {}", isResult);

    }
    @Scheduled(cron = "0 * * * * *")
    public void checkChangeLogs() {
        log.info("call checkChangeLog");
        final List<UserChangeLogDTO> changeLogList = userChangeLogService.getAllUserChangeLog();
        changeLogList.forEach(changLog -> {
            log.info("change Log >> {}", changLog);
        });
        log.info("check log completed!!");
    }
}
