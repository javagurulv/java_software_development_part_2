package lv.javaguru.travel.insurance.core.services.information_to_file_saver;

import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import lv.javaguru.travel.insurance.core.services.process_agreement_service.SendAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class XMLTask {
    private ThreadPoolExecutor executor;
    @Autowired
    private SendAgreementService sendAgreementService;
    private final String filepath = "revinskaya_ekaterina/insurance_calculator_app/agreements_info_files_xml/276772.xml";
    @Autowired
    private AgreementRepository agreementRepository;

    @Scheduled(fixedDelay = 180000)
    void execute() throws InterruptedException {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executor.execute(new JobSendXML(filepath, sendAgreementService));
    }

}

