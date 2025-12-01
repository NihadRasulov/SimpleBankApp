package BankApp.service.card;

import BankApp.dto.card.CardRequestDto;
import BankApp.dto.card.CardResponseDto;
import BankApp.model.card.Card;
import BankApp.repository.card.CardRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ModelMapper modelMapper;

    public CardResponseDto createCard(CardRequestDto cardRequestDto) {
        Card card = new Card();
        card.setCardNumber(cardRequestDto.getCardNumber());
        card.setExpireDate(cardRequestDto.getExpireDate());
        card.setCvv(cardRequestDto.getCvv());

        cardRepository.save(card);
        return modelMapper.map(card,CardResponseDto.class);
    }


    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public CardResponseDto getById(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        return modelMapper.map(card,CardResponseDto.class);
    }

    public CardResponseDto updateCard(Long id, CardRequestDto cardRequestDto) {
        Optional<Card> card = cardRepository.findById(id);
        card.get().setCardNumber(cardRequestDto.getCardNumber());
        card.get().setExpireDate(cardRequestDto.getExpireDate());
        card.get().setCvv(cardRequestDto.getCvv());
        cardRepository.save(card.get());
        return modelMapper.map(card.get(),CardResponseDto.class);
    }


    public String deleteCard(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        cardRepository.delete(card.get());
        return "Card deleted";
    }
}
