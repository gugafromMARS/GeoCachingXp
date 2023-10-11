package gsc.projects.levelingmcs.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="leveling")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_level")
    private double userLevel;

    public static LevelBuilder builder(){
        return new LevelBuilder();
    }

    public static class LevelBuilder {

        private Level level;

        public LevelBuilder() {
            level = new Level();
        }

        public LevelBuilder withUserEmail(String userEmail){
            level.setUserEmail(userEmail);
            return this;
        }

        public LevelBuilder withUserLevel(double userLevel){
            level.setUserLevel(userLevel);
            return this;
        }

        public Level build(){
            return level;
        }
    }

}
