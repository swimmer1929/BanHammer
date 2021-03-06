package name.richardson.james.bukkit.banhammer;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import name.richardson.james.bukkit.utilities.command.context.CommandContext;
import name.richardson.james.bukkit.utilities.permissions.PermissionManager;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LimitsCommandTest extends TestCase {

	private LimitsCommand command;
	private CommandContext commandContext;
	private Player player;

	@Test
	public void testExecuteNoPermission()
	throws Exception {
		command.execute(commandContext);
		verify(player).sendMessage("§cYou may not view any limits.");
	}

	@Test
	public void testExecute()
	throws Exception {
		when(player.hasPermission(anyString())).thenReturn(true);
		command.execute(commandContext);
		verify(player).sendMessage("§dThere are a total of §b2 limits§d configured.");
		verify(player).sendMessage("§atest (1 minute), §atest2 (10 seconds)");
	}

	private static Map<String, Long> getLimits() {
		Map<String, Long> limits = new HashMap<String, Long>();
		limits.put("test", Long.valueOf(60000));
		limits.put("test2", Long.valueOf(10000));
		return limits;
	}


	@Before
	public void setUp()
	throws Exception {
		player = mock(Player.class);
		when(player.getName()).thenReturn("frank");
		commandContext = mock(CommandContext.class);
		when(commandContext.getCommandSender()).thenReturn(player);
		command = new LimitsCommand(LimitsCommandTest.getLimits());
	}

}
