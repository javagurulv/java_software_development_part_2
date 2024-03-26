package lv.javaguru.travel.insurance.core.services.information_to_file_saver;

import lv.javaguru.travel.insurance.core.services.process_agreement_service.SendAgreementService;

public class JobSendXML implements Runnable {

    private SendAgreementService sendAgreementService;

    private String filename;
    public JobSendXML(String filename, SendAgreementService sendAgreementService) {
        this.filename = filename;
        this.sendAgreementService = sendAgreementService;
    }

    @Override
    public void run() {
        //sendAgreementService.sendAgreementXML(filename);
    }

}
