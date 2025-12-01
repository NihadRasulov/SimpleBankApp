package BankApp.controller.card;

import BankApp.dto.card.CardRequestDto;
import BankApp.dto.card.CardResponseDto;
import BankApp.model.card.Card;
import BankApp.service.card.CardService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/card")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardController {
    @Autowired
    CardService cardService;


    @PostMapping("/createCard")
    public CardResponseDto create(@Valid @RequestBody CardRequestDto cardRequestDto) {
        return cardService.createCard(cardRequestDto);
    }


    @GetMapping("/getAll")
    public List<Card> getAll() {
        return cardService.getAllCards();
    }

    @GetMapping("/getById/{id}")
    public CardResponseDto getById(@PathVariable Long id) {
        return cardService.getById(id);
    }

    @PutMapping("/updateCard")
    public CardResponseDto update(@RequestPart Long id,@Valid @RequestPart CardRequestDto cardRequestDto) {
        return cardService.updateCard(id,cardRequestDto);
    }

    @DeleteMapping("deleteCard/{id}")
    public String delete(@PathVariable Long id) {
        return cardService.deleteCard(id);
    }
}
