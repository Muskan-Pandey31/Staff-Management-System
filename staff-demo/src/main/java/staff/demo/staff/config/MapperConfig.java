package staff.demo.staff.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import staff.demo.staff.dto.StaffDTO;
import staff.demo.staff.entity.StaffEntity;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
