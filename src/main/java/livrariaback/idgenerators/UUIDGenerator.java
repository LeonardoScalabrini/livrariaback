package livrariaback.idgenerators;

import adapter.port.GeneratorId;

import java.util.UUID;

public class UUIDGenerator implements GeneratorId {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
