package com.example.kosmo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


@Entity
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        private String name;

        @NotNull
        @Size(min = 2, max = 50, message = "El apellido paterno debe tener entre 2 y 50 caracteres")
        private String paternalSurname;

        @NotNull
        @Size(min = 2, max = 50, message = "El apellido materno debe tener entre 2 y 50 caracteres")
        private String maternalSurname;

        @NotNull
        private LocalDate dateBirth;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPaternalSurname() {
                return paternalSurname;
        }

        public void setPaternalSurname(String paternalSurname) {
                this.paternalSurname = paternalSurname;
        }

        public String getMaternalSurname() {
                return maternalSurname;
        }

        public void setMaternalSurname(String maternalSurname) {
                this.maternalSurname = maternalSurname;
        }

        public LocalDate getDateBirth() {
                return dateBirth;
        }

        public void setDateBirth(LocalDate dateBirth) {
                this.dateBirth = dateBirth;
        }

        @Override
        public String toString() {
                return "Patient{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", paternalSurname='" + paternalSurname + '\'' +
                        ", maternalSurname='" + maternalSurname + '\'' +
                        ", dateBirth=" + dateBirth +
                        '}';
        }
}
