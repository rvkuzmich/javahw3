package kuzmich.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebConfigTest {

    @Test
    void configureMessageConvertersTest() {
        WebConfig webConfig = new WebConfig();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        webConfig.configureMessageConverters(converters);

        assertNotNull(webConfig);

    }
}