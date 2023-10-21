package gsc.projects.registermcs.model;


import gsc.projects.registermcs.repository.RegisterRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "register")
@Getter
@Setter
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long id;

    @Column(name = "local_date")
    private LocalDate localDate;

    @Column(name = "cache_code")
    private String cacheCode;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_level")
    private double userLevel;

    public static RegisterBuilder builder(){
        return new RegisterBuilder();
    }

    public static class RegisterBuilder {

        private Register register;

        public RegisterBuilder() {
            register = new Register();
        }

        public RegisterBuilder withLocalDate(LocalDate localDate){
            register.setLocalDate(localDate);
            return this;
        }

        public RegisterBuilder withCacheCode(String cacheCode){
            register.setCacheCode(cacheCode);
            return this;
        }

        public RegisterBuilder withUserEmail(String userEmail){
            register.setUserEmail(userEmail);
            return this;
        }

        public RegisterBuilder withUserLevel(double userLevel){
            register.setUserLevel(userLevel);
            return this;
        }

        public Register build(){
            return register;
        }
    }
}
