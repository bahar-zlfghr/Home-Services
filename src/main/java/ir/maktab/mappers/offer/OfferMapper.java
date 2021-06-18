package ir.maktab.mappers.offer;

import ir.maktab.data.domain.Offer;
import ir.maktab.dtos.OfferDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OfferMapper {

    Offer toOffer(OfferDto offerDto);
    OfferDto toOfferDto(Offer offer);
}
