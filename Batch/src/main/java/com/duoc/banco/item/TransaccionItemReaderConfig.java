package com.duoc.banco.item;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.duoc.banco.model.Transaccion;

import static com.duoc.banco.util.LocalDateParser.toFecha;

@Configuration
public class TransaccionItemReaderConfig {

    @Bean(name = "transaccionItemReader")
    @StepScope // Crea una instancia del Bean para cada partición
    public FlatFileItemReader<Transaccion> transaccionItemReader(
        @Value("${app.input-transacciones}") Resource inputFile,
        @Value("#{stepExecutionContext['start']}") int start, // Inyecta el límite inferior de la partición
        @Value("#{stepExecutionContext['end']}") int end // Inyecta el límite superior de la partición
    ) {
        FlatFileItemReader<Transaccion> reader = new FlatFileItemReaderBuilder<Transaccion>()
            .name("transaccionItemReader")
            .resource(inputFile)
            .encoding("UTF-8")
            .linesToSkip(1)
            .delimited()
            .delimiter(",")
            .names("id", "fecha", "monto", "tipo")
            .fieldSetMapper(transaccionFieldSetMapper())
            .build();
        
        reader.setCurrentItemCount(start); // Límite inferior de la partición 
        reader.setMaxItemCount(end + 1); // Límite superior de la partición (se suma 1 porque maxItemCount es exclusivo)

        return reader;
    }

    private FieldSetMapper<Transaccion> transaccionFieldSetMapper() {
        return fieldSet -> {
            Transaccion transaccion = new Transaccion();
            transaccion.setId(fieldSet.readLong("id"));
            transaccion.setFecha(toFecha(fieldSet.readString("fecha")));
            transaccion.setMonto(fieldSet.readInt("monto"));
            transaccion.setTipo(fieldSet.readString("tipo"));
            return transaccion;
        };
    }
}
