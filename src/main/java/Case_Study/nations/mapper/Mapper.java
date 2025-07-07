package Case_Study.nations.mapper;

import Case_Study.nations.model.Countries;
import Case_Study.nations.model.Languages;
import Case_Study.nations.model.MaxGdp;
import Case_Study.nations.model.Stats;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    @Select("select name, area, country_code2 from countries ORDER BY name ASC")
    List<Countries> fetchAllCountries();

    @Select("""
            SELECT
                l.language
            FROM languages AS l
            WHERE l.language_id IN (
              SELECT cl.language_id
              FROM country_languages AS cl
              JOIN countries AS c
                ON cl.country_id = c.country_id
              WHERE c.name =#{name}
            )""")
    List<Languages> fetchLanguagesPerCountry(@Param("name") String name);

    @Select("""
            SELECT
               c.name,
               c.country_code3,
               cs.year,
               cs.population,
               cs.gdp
             FROM countries AS c
             JOIN country_stats AS cs
               ON c.country_id = cs.country_id
             WHERE (cs.gdp / NULLIF(cs.population, 0)) = (
               SELECT MAX(cs2.gdp / NULLIF(cs2.population, 0))
               FROM country_stats AS cs2
               WHERE cs2.country_id = c.country_id
             )order by c.name ASC""")
    List<MaxGdp> fetchMaxGdpPerCountry();

    @Select("""
            SELECT
              ctn.name        AS continent_name,
              rgn.name        AS region_name,
              ctr.name        AS country_name,
              cs.year,
              cs.population,
              cs.gdp
            FROM continents AS ctn
            JOIN regions AS rgn
              ON ctn.continent_id = rgn.continent_id
            JOIN countries AS ctr
              ON rgn.region_id = ctr.region_id
            JOIN country_stats AS cs
              ON ctr.country_id = cs.country_id
            ORDER BY continent_name, region_name, cs.year, country_name ASC""")
    List<Stats> fetchStats();
}
