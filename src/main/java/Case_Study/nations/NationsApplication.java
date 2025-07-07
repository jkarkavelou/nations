package Case_Study.nations;

import Case_Study.nations.model.Countries;
import Case_Study.nations.model.Languages;
import Case_Study.nations.model.MaxGdp;
import Case_Study.nations.model.Stats;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes({Countries.class, Languages.class, MaxGdp.class, Stats.class})
@MapperScan("Case_Study.nations.mapper")
@SpringBootApplication
public class NationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NationsApplication.class, args);
	}
}
