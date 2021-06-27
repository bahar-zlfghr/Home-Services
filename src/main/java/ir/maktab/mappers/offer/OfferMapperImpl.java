package ir.maktab.mappers.offer;

import ir.maktab.data.domain.Offer;
import ir.maktab.dtos.OfferDto;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class OfferMapperImpl implements OfferMapper {
    private final OrderMapper orderMapper;
    private final SpecialistMapper specialistMapper;

    @Lazy
    public OfferMapperImpl(OrderMapper orderMapper, SpecialistMapper specialistMapper) {
        this.orderMapper = orderMapper;
        this.specialistMapper = specialistMapper;
    }

    @Override
    public Offer toOffer(OfferDto offerDto) {
        if (offerDto != null) {
            return new Offer()
                    .setId(offerDto.getId())
                    .setSubmitDate(offerDto.getSubmitDate())
                    .setSuggestedPrice(offerDto.getSuggestedPrice())
                    .setDurationDoWork(offerDto.getDurationDoWork())
                    .setStartTime(offerDto.getStartTime())
                    .setOrder(orderMapper.toOrder(offerDto.getOrderDto()))
                    .setSpecialist(specialistMapper.toSpecialist(offerDto.getSpecialistDto()))
                    .setStatus(offerDto.getStatus());
        }
        return null;
    }

    @Override
    public OfferDto toOfferDto(Offer offer) {
        if (offer != null) {
            return new OfferDto()
                    .setId(offer.getId())
                    .setSubmitDate(offer.getSubmitDate())
                    .setSuggestedPrice(offer.getSuggestedPrice())
                    .setDurationDoWork(offer.getDurationDoWork())
                    .setStartTime(offer.getStartTime())
                    .setOrderDto(orderMapper.toOrderDto(offer.getOrder()))
                    .setSpecialistDto(specialistMapper.toSpecialistDto(offer.getSpecialist()))
                    .setStatus(offer.getStatus());
        }
        return null;
    }
}
