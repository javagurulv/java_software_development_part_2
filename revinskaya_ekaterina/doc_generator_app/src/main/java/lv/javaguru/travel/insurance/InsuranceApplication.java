package lv.javaguru.travel.insurance;

import lv.javaguru.travel.insurance.core.servises.conver_to_pdf.ConvertToPDFServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class InsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceApplication.class, args);
    }



}
