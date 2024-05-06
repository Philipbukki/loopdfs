package org.smunyau.loopdfs.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.smunyau.loopdfs.dto.CardType;

@Entity
@Table(name="cards")
@NoArgsConstructor
@Getter @Setter
@Schema(
        name = "Cards",
        description = "Table holding Card Details"

)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false)
//    @Schema(hidden = true)
    @JsonIgnore
    private Long cardId;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_type", updatable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Schema(hidden = true)
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "account_id")
    private Account account;

}
