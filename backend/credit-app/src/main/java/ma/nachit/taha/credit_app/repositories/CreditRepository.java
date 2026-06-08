package ma.nachit.taha.credit_app.repositories;

import ma.nachit.taha.credit_app.entities.Credit;
import ma.nachit.taha.credit_app.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClientId(Long clientId);
    List<Credit> findByStatut(StatutCredit statut);
}
