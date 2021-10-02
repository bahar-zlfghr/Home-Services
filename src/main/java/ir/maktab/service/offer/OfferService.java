package ir.maktab.service.offer;

import ir.maktab.data.enums.OfferStatus;
import ir.maktab.dtos.OfferDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.exceptions.InvalidSpecialistSubService;
import ir.maktab.exceptions.InvalidSuggestedPriceException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OfferService {

    void saveOffer(OfferDto offerDto) throws InvalidSuggestedPriceException, InvalidSpecialistSubService;
    void updateOfferSuggestedPrice(Integer id, Long suggestedPrice);
    void updateOfferStatus(Integer id, OfferStatus offerStatus);
    void deleteOffer(OfferDto offerDto);
    Set<OfferDto> getOffersBySpecialist(SpecialistDto specialistDto);
    Set<OfferDto> getOffersByOrder(OrderDto orderDto);
}
