package nl.simpliphi.indexingservice.config.elasticsearch;

import nl.simpliphi.indexingservice.config.elasticsearch.converters.LongToInstantConverter;
import nl.simpliphi.indexingservice.config.elasticsearch.converters.StringConverter;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

import java.util.Arrays;

@Configuration
public class ESConfig {

  @Value("${spring.elasticsearch.rest.uris}")
  protected String host;

  @Bean
  public RestHighLevelClient client() {
    ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(host)
            .build();

    return RestClients.create(clientConfiguration).rest();
  }

  @Bean
  public SimpleElasticsearchMappingContext elasticsearchMappingContext() {
    return new SimpleElasticsearchMappingContext();
  }

  @Bean
  public ElasticsearchCustomConversions elasticsearchCustomConversions() {
    return new ElasticsearchCustomConversions(Arrays.asList(
        new StringConverter(),
        new LongToInstantConverter()
    ));
  }

  @Bean
  public ElasticsearchConverter elasticsearchConverter(SimpleElasticsearchMappingContext elasticsearchMappingContext, ElasticsearchCustomConversions elasticsearchCustomConversions) {
    MappingElasticsearchConverter elasticsearchConverter = new MappingElasticsearchConverter(elasticsearchMappingContext);
    elasticsearchConverter.setConversions(elasticsearchCustomConversions);

    return elasticsearchConverter;
  }

  @Bean
  public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient client, ElasticsearchConverter elasticsearchConverter) {
    return new ElasticsearchRestTemplate(client, elasticsearchConverter);
  }

}
