package ma.nachit.taha.credit_app.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.nachit.taha.credit_app.enums.TypeBienImmobilier;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Getter @Setter
@NoArgsConstructor
public class CreditImmobilier extends Credit {

    @Enumerated(EnumType.STRING)
    private TypeBienImmobilier typeBien;
}
