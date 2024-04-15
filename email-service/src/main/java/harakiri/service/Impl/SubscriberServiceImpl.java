package harakiri.service.Impl;

import harakiri.models.Subscriberbd;
import harakiri.repository.SubscriberRepository;
import harakiri.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public void save(Subscriberbd s) {
        subscriberRepository.save(s);
    }
}
