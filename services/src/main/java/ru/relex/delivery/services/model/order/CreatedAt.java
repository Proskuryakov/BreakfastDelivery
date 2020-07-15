package ru.relex.delivery.services.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.time.Instant;

@Value.Immutable
@Value.Style(defaultAsDefault = true)
@JsonDeserialize(builder = ImmutableCreatedAt.Builder.class)

public interface CreatedAt {
    default String getCreatedAt() {
        return Instant.now().toString();
    }

}