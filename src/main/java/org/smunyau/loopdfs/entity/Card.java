package org.smunyau.loopdfs.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.smunyau.loopdfs.utilities.CardType;

@Entity
@Table(name="cards")
@NoArgsConstructor
@Getter @Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false)
    private Long cardId;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_type", updatable = false)
    private CardType cardType;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "account_id")
    private Account account;

}
