package org.ifsim.vairline.common.util;

/**
 * @Description: 机型名称转换
 * @author shentong
 * @date 2017年11月17日 下午5:17:28
 * @version V1.0
 */
public class ModelUtil {
	public static String getGentitle(String model, String company) {
		model = model.trim();
		company = "CYA";

		switch (model) {
		case "A301":
		case "A302":
		case "A306":
		case "A30B":
			model = "A306";
			break;

		case "B707":
			model = "B701";
			break;

		case "B727":
			model = "B722";
			break;

		case "B747":
			model = "B742";
			break;

		case "B751":
		case "B757":
			model = "B752";
			break;

		case "B761":
		case "B767":
			model = "B762";
			break;

		case "A318CFM":
		case "A318IAE":
			model = "A318";
			break;

		case "A319CFM":
		case "A319IAE":
			model = "A319";
			break;

		case "A320CFM":
		case "A320IAE":
			model = "A320";
			break;

		case "A321CFM":
		case "A321IAE":
			model = "A321";
			break;

		case "A330-200":
			model = "A332";
			break;

		case "A330-300":
			model = "A333";
			break;

		case "1011":
			model = "L1011";
			break;

		case "CARJ":
		case "CRJ700":
			model = "CRJ7";
			break;

		case "CRJ900":
			model = "CRJ9";
			break;

		case "E-170":
		case "E-175":
			model = "E170";
			break;

		case "E-190":
		case "E-195":
			model = "E190";
			break;

		case "J10":
		case "J11":
		case "L29":
			model = "L39";
			break;
		}

		switch (model) {
		case "808s":
			return "DG808S";

		case "trike":
			return "Aircreation582SL";

		case "J3":
			return "Piper Cub Paint1";

		case "M7":
			return "Maule M7 260C Paint1";

		case "ARCHER":
			return "Piper Cherokee 180 Paint1";

		case "DHC2":
			return "DeHavilland Beaver DHC2 Paint1";

		case "C172":
			return "Cessna Skyhawk 172SP Paint1";

		case "M20T":
			return "Mooney Bravo Retro";

		case "BE58":
			return "Beech Baron 58 Paint1";

		case "C208":
			return "Cessna Grand Caravan Paint1";

		case "G21":
			return "Grumman Goose G21A Paint1";

		case "B350":
			return "Beech King Air 350 Paint1";

		case "DC3":
			return "Douglas DC-3 Paint1";

		case "C47":
			return "Douglas DC-3 Paint4";

		case "E300":
			return "Extra 300S Paint1";

		case "Mustang":
			return "P51 Racer Paint4";

		case "LJ35":
		case "LJ45":
		case "C550":
		case "C750":
		case "Victory":
			return "Learjet 45 Limited Edition";

		case "DHC8D":
		case "DH8A":
			return "de Havilland Dash 8-100 Paint1";

		case "MD80":
		case "MD87":
		case "MD90":
			return "McDonnell-Douglas/Boeing MD-83 Paint1";

		case "F18":
			return "FA-18 Hornet Paint6";

		case "A318":
		case "A319":
		case "A320":
		case "A321":
		case "A332":
		case "A333":
		case "A342":
		case "A343":
		case "A345":
		case "A346":
		case "A388":
		case "B701":
		case "B703":
		case "B721":
		case "B722":
		case "B732":
		case "B733":
		case "B734":
		case "B735":
		case "B736":
		case "B737":
		case "B738":
		case "B739":
		case "B742":
		case "B743":
		case "B744":
		case "B752":
		case "B753":
		case "B761":
		case "B762":
		case "B763":
		case "B764":
		case "B772":
		case "B773":
		case "B77L":
		case "B77W":
		case "B788":
		case "C130":
		case "CRJ2":
		case "CRJ7":
		case "CRJ9":
		case "CRJX":
		case "E135":
		case "E145":
		case "E170":
		case "E190":
		case "MD11":
		case "L1011":
		case "L39":
		case "F14":
		case "F15":
		case "F16":
		case "F22":
			return "FSCYA_" + model + "_" + company;
		case "F35":
			return "FSCYA_" + "F35A" + "_" + company;
		case "EH10":
		case "R22":
		case "helicopter":
			return "";

		default:
			return "Cessna Skyhawk 172SP";
		}
	}
}
