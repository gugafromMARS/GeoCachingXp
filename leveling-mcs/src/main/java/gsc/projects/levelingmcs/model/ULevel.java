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
public class ULevel {
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

        private ULevel uLevel;

        public LevelBuilder() {
            uLevel = new ULevel();
        }

        public LevelBuilder withUserEmail(String userEmail){
            uLevel.setUserEmail(userEmail);
            return this;
        }

        public LevelBuilder withUserLevel(double userLevel){
            uLevel.setUserLevel(userLevel);
            return this;
        }

        public ULevel build(){
            return uLevel;
        }
    }

}
