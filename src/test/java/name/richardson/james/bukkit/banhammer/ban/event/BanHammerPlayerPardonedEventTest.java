package name.richardson.james.bukkit.banhammer.ban.event;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import name.richardson.james.bukkit.banhammer.ban.BanRecord;
import name.richardson.james.bukkit.banhammer.ban.PlayerRecord;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BanHammerPlayerPardonedEventTest extends TestCase {

	private BanHammerPlayerPardonedEvent event;

	@Test
	public void testGetHandlers()
	throws Exception {
		Assert.assertEquals("Handler lists should be set!", BanHammerPlayerPardonedEvent.getHandlerList(), event.getHandlers());
	}

	@Before
	public void setUp()
	throws Exception {
		BanRecord banRecord = mock(BanRecord.class);
		PlayerRecord playerRecord = mock(PlayerRecord.class);
		when(banRecord.getPlayer()).thenReturn(playerRecord);
		event = new BanHammerPlayerPardonedEvent(banRecord, true);
	}

}
