package sbz.project.Application.config;

import org.drools.template.DataProviderCompiler;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieContainerConfig {

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbz.project", "Kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(5_000);
        return kContainer;
    }

    @Bean
    public KieBaseConfiguration baseConfiguration() {
        KieServices ks = KieServices.Factory.get();
        KieBaseConfiguration baseConf = ks.newKieBaseConfiguration();
        baseConf.setOption(EventProcessingOption.STREAM);
        return baseConf;
    }

    @Bean
    public KieHelper kieHelper() {
        return new KieHelper();
    }

    @Bean
    public KieSession kieSession() {
        return kieContainer().newKieSession("alarmConfigKsessionRealtimeClock");
    }

//    @Bean
//    public ObjectDataCompiler objectDataCompiler() {
//        return new ObjectDataCompiler();
//    }


}
