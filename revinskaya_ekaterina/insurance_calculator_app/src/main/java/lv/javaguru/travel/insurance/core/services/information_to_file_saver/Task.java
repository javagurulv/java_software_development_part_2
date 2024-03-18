package lv.javaguru.travel.insurance.core.services.information_to_file_saver;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class Task {

    private final String filepath = "revinskaya_ekaterina/insurance_calculator_app/agreements_info_files_xml/";
    @Autowired
    private AgreementRepository agreementRepository;
    @Autowired
    private AgreementInformationBuilder agreementInformationBuilder;
    @Autowired
    private WriterToFile writerToFile;
    private ThreadPoolExecutor executor;

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Scheduled(cron = "40 17 20 * * *")
    void execute() throws InterruptedException {
        List<Long> listIds = getIds();
        Stopwatch stopwatch = Stopwatch.createStarted();
        while (!listIds.isEmpty()) {
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            System.out.println(stopwatch.elapsed().toMillis());
            scheduler.schedule(() -> {
                System.out.println(stopwatch.elapsed().toMillis());
                long completedTasks = executor.getCompletedTaskCount();
              //  System.out.println("num of tasks: " + completedTasks);
            }, 5, TimeUnit.SECONDS);
            for (var id : listIds) {
                executor.execute(new JobCreateXML(id, filepath,
                        agreementInformationBuilder, agreementRepository, writerToFile));
            }
            System.out.println(listIds.get(0) + "____" + listIds.get(listIds.size() - 1));
            try {
                executor.awaitTermination(2, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            listIds = getIds();
        }
    }



    private List<Long> getIds() {
        Pageable pageable = PageRequest.of(0, 1000000);
        return agreementRepository.findNotAlreadyExportedId(pageable);
    }
}
