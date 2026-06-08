package ma.nachit.taha.credit_app;

import ma.nachit.taha.credit_app.entities.*;
import ma.nachit.taha.credit_app.enums.StatutCredit;
import ma.nachit.taha.credit_app.enums.TypeBienImmobilier;
import ma.nachit.taha.credit_app.enums.TypeRemboursement;
import ma.nachit.taha.credit_app.repositories.ClientRepository;
import ma.nachit.taha.credit_app.repositories.CreditRepository;
import ma.nachit.taha.credit_app.repositories.RemboursementRepository;
import ma.nachit.taha.credit_app.security.AppUser;
import ma.nachit.taha.credit_app.security.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CreditAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            appUserRepository.save(new AppUser(null, "admin", passwordEncoder.encode("123"),
                    List.of("ROLE_ADMIN", "ROLE_EMPLOYE")));
            appUserRepository.save(new AppUser(null, "employe", passwordEncoder.encode("123"),
                    List.of("ROLE_EMPLOYE")));
            appUserRepository.save(new AppUser(null, "client", passwordEncoder.encode("123"),
                    List.of("ROLE_CLIENT")));
        };
    }

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository,
                                   CreditRepository creditRepository,
                                   RemboursementRepository remboursementRepository) {
        return args -> {
            List<String> noms = List.of("Alami", "Bennani", "Cherkaoui", "Daoudi", "El Idrissi");
            for (String nom : noms) {
                Client client = new Client();
                client.setNom(nom);
                client.setEmail(nom.toLowerCase().replace(" ", "") + "@gmail.com");
                clientRepository.save(client);
            }

            List<Client> clients = clientRepository.findAll();

            for (int i = 0; i < 12; i++) {
                Client client = clients.get(i % clients.size());
                Credit credit;
                int type = i % 3;
                if (type == 0) {
                    CreditPersonnel cp = new CreditPersonnel();
                    cp.setMotif(i % 2 == 0 ? "Achat voiture" : "Etudes");
                    credit = cp;
                } else if (type == 1) {
                    CreditImmobilier ci = new CreditImmobilier();
                    ci.setTypeBien(TypeBienImmobilier.values()[i % TypeBienImmobilier.values().length]);
                    credit = ci;
                } else {
                    CreditProfessionnel cpr = new CreditProfessionnel();
                    cpr.setMotif("Investissement");
                    cpr.setRaisonSociale("Entreprise " + i);
                    credit = cpr;
                }
                credit.setClient(client);
                credit.setDateDemande(LocalDate.now().minusMonths(i));
                credit.setStatut(StatutCredit.values()[i % StatutCredit.values().length]);
                credit.setMontant(50000.0 + i * 10000);
                credit.setDureeRemboursement(12 + i * 6);
                credit.setTauxInteret(3.5 + (i % 3));
                if (credit.getStatut() == StatutCredit.ACCEPTE) {
                    credit.setDateAcceptation(credit.getDateDemande().plusDays(7));
                }
                creditRepository.save(credit);
            }

            List<Credit> credits = creditRepository.findAll();

            for (int i = 0; i < 24; i++) {
                Credit credit = credits.get(i % credits.size());
                Remboursement r = new Remboursement();
                r.setCredit(credit);
                r.setDate(LocalDate.now().minusDays(i * 5L));
                r.setMontant(1500.0 + i * 100);
                r.setType(i % 4 == 0 ? TypeRemboursement.REMBOURSEMENT_ANTICIPE : TypeRemboursement.MENSUALITE);
                remboursementRepository.save(r);
            }
        };
    }
}
