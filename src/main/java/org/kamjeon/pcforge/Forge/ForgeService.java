package org.kamjeon.pcforge.Forge;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ForgeService {
	private final ForgeRepository forgeRepository;
}
