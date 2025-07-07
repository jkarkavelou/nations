package Case_Study.nations.resource;

import Case_Study.nations.mapper.Mapper;
import Case_Study.nations.model.Countries;
import Case_Study.nations.model.Languages;
import Case_Study.nations.model.MaxGdp;
import Case_Study.nations.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/nations")
public class NationsResource {
    @Autowired
    Mapper mapper;

    @GetMapping("/countries")
    public List<Countries> getAll(){
        return mapper.fetchAllCountries();
    }

    @GetMapping("/country-languages/{name}")
    public List<Languages> getLanguages(@PathVariable String name){
        return mapper.fetchLanguagesPerCountry(name);
    }

    @GetMapping("/max-gdp")
    public List<MaxGdp> getLanguages(){
        return mapper.fetchMaxGdpPerCountry();
    }

    @GetMapping("/stats")
    public List<Stats> getStats(){
        return mapper.fetchStats();
    }
}
