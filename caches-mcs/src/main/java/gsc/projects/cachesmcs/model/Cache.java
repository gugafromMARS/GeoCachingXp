package gsc.projects.cachesmcs.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String address;

    private String description;

    private String tip;

    @Enumerated(value = EnumType.STRING)
    private DifficultyLevel difficulty;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ground_level")
    private GroundLevel groundLevel;

    @Enumerated(value = EnumType.STRING)
    @Column(name ="cache_size")
    private CacheSize cacheSize;

    private int cacheLevel;

    @Column(name = "experience_earned")
    private double experienceEarned;

    private String cacheCode;

    public static CacheBuilder builder(){
        return new CacheBuilder();
    }

    public static class CacheBuilder {

        private Cache cache;

        public CacheBuilder() {
            cache = new Cache();
        }

        public CacheBuilder withAddress(String address){
            cache.setAddress(address);
            return this;
        }

        public CacheBuilder withDescription(String description){
            cache.setDescription(description);
            return this;
        }

        public CacheBuilder withTip(String tip){
            cache.setTip(tip);
            return this;
        }

        public CacheBuilder withDifficulty(DifficultyLevel difficulty){
            cache.setDifficulty(difficulty);
            return this;
        }

        public CacheBuilder withGroundLevel(GroundLevel groundLevel){
            cache.setGroundLevel(groundLevel);
            return this;
        }

        public CacheBuilder withSize(CacheSize cacheSize){
            cache.setCacheSize(cacheSize);
            return this;
        }

        public CacheBuilder withCacheLevel(int cacheLevel){
            cache.setCacheLevel(cacheLevel);
            return this;
        }

        public CacheBuilder withExperienceEarned(){
            double exp = (double) cache.getCacheLevel() / 10;
            cache.setExperienceEarned(exp);
            return this;
        }

        public CacheBuilder withCacheCode(String code){
            String[] newCode = code.trim().split(" ");
            String checkedCode = "";
            for(String s : newCode){
                checkedCode += s;
            }
            cache.setCacheCode(checkedCode);
            return this;
        }

        public Cache build(){
            return cache;
        }
    }

}
