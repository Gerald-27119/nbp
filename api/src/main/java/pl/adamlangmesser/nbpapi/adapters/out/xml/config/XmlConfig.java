package pl.adamlangmesser.nbpapi.adapters.out.xml.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlConfig {

    @Bean
    XmlMapper xmlMapper() {
        return XmlMapper.builder()
                .findAndAddModules()
                .build();
    }
}
