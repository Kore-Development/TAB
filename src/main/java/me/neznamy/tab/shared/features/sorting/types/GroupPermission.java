package me.neznamy.tab.shared.features.sorting.types;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.shared.Shared;
import me.neznamy.tab.shared.config.Configs;

public class GroupPermission extends SortingType {

	public GroupPermission(String sortingPlaceholder) {
		super(sortingPlaceholder);
	}

	@Override
	public String getChars(TabPlayer p) {
		String chars = null;
		for (String localgroup : Configs.sortedGroups.keySet()) {
			if (p.hasPermission("tab.sort." + localgroup)) {
				chars = Configs.sortedGroups.get(localgroup.toLowerCase());
				p.setTeamNameNote("Highest sorting permission: &etab.sort." + localgroup + " &a(#" + Integer.parseInt(chars) + " in sorting list)");
				if (p.hasPermission("random.permission")) {
					p.setTeamNameNote(p.getTeamNameNote() + ". &cThis user appears to have all permissions. Is he OP?");
				}
				break;
			}
		}
		if (chars == null) {
			chars = "";
			Shared.errorManager.oneTimeConsoleError("Sorting by permissions is enabled but player " + p.getName() + " does not have any sorting permission. Configure sorting permissions or disable sorting by permissions like it is by default.");
			p.setTeamNameNote("&cPlayer does not have sorting permission for any group in sorting list");
		}
		return chars;
	}
	
	@Override
	public String toString() {
		return "GROUP_PERMISSIONS";
	}
}