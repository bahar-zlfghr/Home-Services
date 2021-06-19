package ir.maktab.service.offer;

import ir.maktab.data.enums.OfferStatus;
import ir.maktab.data.enums.OrderStatus;
import ir.maktab.data.repository.offer.OfferRepository;
import ir.maktab.data.repository.order.OrderRepository;
import ir.maktab.dtos.OfferDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.exceptions.InvalidSpecialistSubService;
import ir.maktab.exceptions.InvalidSuggestedPriceException;
import ir.maktab.mappers.offer.OfferMapper;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final SpecialistMapper specialistMapper;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OfferServiceImpl(OfferRepository offerRepository, OfferMapper offerMapper, SpecialistMapper specialistMapper, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.specialistMapper = specialistMapper;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public void saveOffer(OfferDto offerDto) throws InvalidSuggestedPriceException, InvalidSpecialistSubService {
        OrderDto orderDto = offerDto.getOrderDto();

        if (!offerDto.getSpecialistDto().getSubServiceDtos().contains(orderDto.getSubServiceDto())) {
            throw new InvalidSpecialistSubService("invalid.specialist.sub.service");
        }

        if (offerDto.getSuggestedPrice() < orderDto.getSubServiceDto().getBasePrice()) {
            throw new InvalidSuggestedPriceException("invalid.suggested.price");
        }

        offerDto.setStatus(OfferStatus.NOT_ACCEPTED);
        offerRepository.save(offerMapper.toOffer(offerDto));

        orderDto.setStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        orderRepository.updateOrderStatus(orderDto.getId(), orderDto.getStatus());
    }

    @Override
    public void updateOfferSuggestedPrice(Integer id, Long suggestedPrice) {
        offerRepository.updateOfferSuggestedPrice(id, suggestedPrice);
    }

    @Override
    public void updateOfferStatus(Integer id, OfferStatus offerStatus) {
        offerRepository.updateOfferStatus(id, offerStatus);
    }

    @Override
    public void deleteOffer(OfferDto offerDto) {
        offerRepository.delete(offerMapper.toOffer(offerDto));
    }

    @Override
    public Set<OfferDto> getOffersBySpecialist(SpecialistDto specialistDto) {
        return offerRepository.getOffersBySpecialist(specialistMapper.toSpecialist(specialistDto))
                .stream().map(offerMapper::toOfferDto).collect(Collectors.toSet());
    }

    @Override
    public Set<OfferDto> getOffersByOrder(OrderDto orderDto) {
        return offerRepository.getOffersByOrder(orderMapper.toOrder(orderDto))
                .stream().map(offerMapper::toOfferDto).collect(Collectors.toSet());
    }
}
