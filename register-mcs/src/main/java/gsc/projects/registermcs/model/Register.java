package gsc.projects.registermcs.model;


import gsc.projects.registermcs.repository.RegisterRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "table")
@Getter
@Setter
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long id;

    @Column(name = "local_date")
    private LocalDate localDate;

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

        public Register build(){
            return register;
        }
    }
}
