package warp.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import warp.properties.FrameworkProperties;
import warp.utilities.math.Maths;

public class NameGenerator {

	//Name Fragment Database
	final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";	
	private static HashMap<Integer, String> prefix = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> suffix = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootA  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootB  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootC  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootD  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootE  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootF  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootG  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootH  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootI  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootJ  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootK  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootL  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootM  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootN  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootO  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootP  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootQ  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootR  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootS  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootT  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootU  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootV  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootW  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootX  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootY  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> rootZ  = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> maleNames 	= new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> femaleNames = new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> lastNames 	= new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> shipNames 	= new HashMap<>() {private static final long serialVersionUID = 1L;};
	private static HashMap<Integer, String> shipPrefix 	= new HashMap<>() {private static final long serialVersionUID = 1L;};

	//////////////LEGACY NEEDS TO BE CHANGED TO ARRAY FROM HASH MAP////////////////////////////
	//Each name is unique, no names repeated in one universe
	private static HashMap<String, Boolean> assignedLatinNames 	= new HashMap<>(){private static final long serialVersionUID = 1L;};
	private static HashMap<String, Boolean> assignedMaleNames 	= new HashMap<>(){private static final long serialVersionUID = 1L;};
	private static HashMap<String, Boolean> assignedLastNames 	= new HashMap<>(){private static final long serialVersionUID = 1L;};
	private static HashMap<String, Boolean> assignedFemaleNames = new HashMap<>(){private static final long serialVersionUID = 1L;};
	private static HashMap<String, Boolean> assignedShipNames 	= new HashMap<>(){private static final long serialVersionUID = 1L;};
	
	private static ArrayList<String> assignedCustomNames = new ArrayList<>();
	
	
	final static Set<String> identifiers = new HashSet<String>();

	
	public static boolean newCustomName(String name) {
		for(String i : assignedCustomNames) {
			if(i == name) {
				if(FrameworkProperties.DEBUG) System.err.println("ERROR! -> NameGenerator -> newCustomName -> failed to add name " + name + " as it already exists");
				return false;
			}
		}
		assignedCustomNames.add(name);
		return true;
	
			
	}
	
	public static String randomIdentifier() {
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = Maths.random.nextInt(5)+5;
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(Maths.random.nextInt(lexicon.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
	}
	public static String getRandomLatin() {
		boolean gotName = false;
		String result = generateRandomLatin();
		while(!gotName) {
			if(!assignedLatinNames.containsKey(result)) {
				gotName = true;
			} else result = generateRandomLatin();
		}
		assignedLatinNames.put(result, true);
		result = result.substring(0,1).toUpperCase() + result.substring(1).toLowerCase();
		return result;
	}
	public static String getShipName() {
		boolean gotName = false;
		String result = generateShipName();
		while(!gotName) {
			if(!assignedShipNames.containsKey(result)) {
				gotName = true;
			} else result = generateShipName();
		}
		assignedShipNames.put(result, true);
		return result;
	}
	public static String getMaleName() {
		String result = getRandMaleName();
		boolean gotName = false;
		while(!gotName) {
			if(!assignedMaleNames.containsKey(result)) {
				gotName = true;
			} else result = generateMaleName();
		}
		result += " " + getLastName();		
		assignedMaleNames.put(result, true);
		return result;
	}
	public static String getLastName() {
		String result = getRandLastName();
		boolean gotName = false;
		while(!gotName) {
			if(!assignedLastNames.containsKey(result)) {
				gotName = true;
			} else result = getRandLastName();
		}
		return result;
	}
	public static String getFemaleName() {
		String result = getRandFemaleName();
		boolean gotName = false;
		while(!gotName) {
			if(!assignedFemaleNames.containsKey(result)) {
				gotName = true;
			} else result = generateFemaleName();
		}
		assignedFemaleNames.containsKey(result);
		return result;
	}

	
	private static String generateShipName() {
		String result = "";
		int r = Maths.random.nextInt(2);
		if(r == 1)result = getRandShipPrefix();
		result += getRandShipSuffix();
		return result;
	}
	private static String generateFemaleName() {
		String result = getRandFemaleName();
		result += getRandLastName();
		return result;
	}
	private static String generateMaleName() {
		String result = getRandMaleName();
		result += getRandLastName();
		return result;
	}
	private static String generateRandomLatin() {
		String result = "";
		for(int i = 0; i < 2 + Maths.random.nextInt(1); i++) {
			result += getRandomRoot();
		}
		int rnd = Maths.random.nextInt(3);
		if(rnd == 1) {			
			result += " ";
			String snd = getRandomRoot();			
			result += snd;
		}
		return result;
	}
	private static String getRandomRoot() {
		String result = "";
		switch(Maths.random.nextInt(25)) { 
			case 0:  result = getRandRootA(); break;
			case 1:  result = getRandRootB(); break;
			case 2:  result = getRandRootC(); break;
			case 3:  result = getRandRootD(); break;
			case 4:  result = getRandRootE(); break;
			case 5:  result = getRandRootF(); break;
			case 6:  result = getRandRootG(); break;
			case 7:  result = getRandRootH(); break;
			case 8:  result = getRandRootI(); break;
			case 9:  result = getRandRootJ(); break;
			case 10: result = getRandRootK(); break;
			case 11: result = getRandRootL(); break;
			case 12: result = getRandRootM(); break;
			case 13: result = getRandRootN(); break;
			case 14: result = getRandRootO(); break;
			case 15: result = getRandRootP(); break;
			case 16: result = getRandRootQ(); break;
			case 17: result = getRandRootR(); break;
			case 18: result = getRandRootS(); break;
			case 19: result = getRandRootT(); break;
			case 20: result = getRandRootU(); break;
			case 21: result = getRandRootV(); break;
			case 22: result = getRandRootW(); break;
			case 23: result = getRandRootX(); break;
			case 24: result = getRandRootY(); break;
			case 25: result = getRandRootZ(); break;	
		}
		return result;
	}
	private static String getRandRootA() {return rootA.get(Maths.random.nextInt(rootA.size()));}
	private static String getRandRootB() {return rootB.get(Maths.random.nextInt(rootB.size()));}
	private static String getRandRootC() {return rootC.get(Maths.random.nextInt(rootC.size()));}
	private static String getRandRootD() {return rootD.get(Maths.random.nextInt(rootD.size()));}
	private static String getRandRootE() {return rootE.get(Maths.random.nextInt(rootE.size()));}
	private static String getRandRootF() {return rootF.get(Maths.random.nextInt(rootF.size()));}
	private static String getRandRootG() {return rootG.get(Maths.random.nextInt(rootG.size()));}
	private static String getRandRootH() {return rootH.get(Maths.random.nextInt(rootH.size()));}
	private static String getRandRootI() {return rootI.get(Maths.random.nextInt(rootI.size()));}
	private static String getRandRootJ() {return rootJ.get(Maths.random.nextInt(rootJ.size()));}
	private static String getRandRootK() {return rootK.get(Maths.random.nextInt(rootK.size()));}
	private static String getRandRootL() {return rootL.get(Maths.random.nextInt(rootL.size()));}
	private static String getRandRootM() {return rootM.get(Maths.random.nextInt(rootM.size()));}
	private static String getRandRootN() {return rootN.get(Maths.random.nextInt(rootN.size()));}
	private static String getRandRootO() {return rootO.get(Maths.random.nextInt(rootO.size()));}
	private static String getRandRootP() {return rootP.get(Maths.random.nextInt(rootP.size()));}
	private static String getRandRootQ() {return rootQ.get(Maths.random.nextInt(rootQ.size()));}
	private static String getRandRootR() {return rootR.get(Maths.random.nextInt(rootR.size()));}
	private static String getRandRootS() {return rootS.get(Maths.random.nextInt(rootS.size()));}
	private static String getRandRootT() {return rootT.get(Maths.random.nextInt(rootT.size()));}
	private static String getRandRootU() {return rootU.get(Maths.random.nextInt(rootQ.size()));}
	private static String getRandRootV() {return rootV.get(Maths.random.nextInt(rootV.size()));}
	private static String getRandRootW() {return rootW.get(Maths.random.nextInt(rootW.size()));}
	private static String getRandRootX() {return rootX.get(Maths.random.nextInt(rootX.size()));}
	private static String getRandRootY() {return rootY.get(Maths.random.nextInt(rootY.size()));}
	private static String getRandRootZ() {return rootZ.get(Maths.random.nextInt(rootZ.size()));}
	private static String getRandShipPrefix() {return shipPrefix.get( Maths.random.nextInt(shipPrefix.size()));}
	private static String getRandShipSuffix() {return shipNames.get(  Maths.random.nextInt(shipNames.size()));}
	private static String getRandMaleName()   {return maleNames.get(  Maths.random.nextInt(maleNames.size()));}
	private static String getRandFemaleName() {return femaleNames.get(Maths.random.nextInt(femaleNames.size()));}
	private static String getRandLastName()   {return lastNames.get(  Maths.random.nextInt(lastNames.size()));}
	
	public static void initHashMaps() {
		initPrefix();
		initSuffix();
		initRoot();
		initMaleNames();
		initFemaleNames();
		initLastNames();
		initShipNames();
		initShipPrefix();
	}
	private static void initShipPrefix() {
		shipPrefix.put(0, "SS ");
		shipPrefix.put(1, "The ");
		shipPrefix.put(2, "USS ");
		shipPrefix.put(3, "UNS ");
		shipPrefix.put(4, "All ");
		shipPrefix.put(5, "Pure ");
		shipPrefix.put(6, "Big ");
		shipPrefix.put(8, "The ");
		shipPrefix.put(9, "The ");
	}
	private static void initShipNames() {
		shipNames.put(0,  "Adventure");
		shipNames.put(1,  "Abstract");
		shipNames.put(2,  "Affinity");
		shipNames.put(3,  "Adaptive");
		shipNames.put(4,  "Accurate");
		shipNames.put(5,  "Acceptance");
		shipNames.put(6,  "Affluent");
		shipNames.put(7,  "Aperture");
		shipNames.put(8,  "Analytic");
		shipNames.put(10, "Amenable");
		shipNames.put(11, "Blessing");
		shipNames.put(12, "Buttress");
		shipNames.put(13, "Becoming");
		shipNames.put(14, "Boundary");
		shipNames.put(15, "Bluebird");
		shipNames.put(16, "Botanist");
		shipNames.put(17, "Brighten");
		shipNames.put(18, "Blossom");
		shipNames.put(19, "Boyant");
		shipNames.put(20, "Creation");
		shipNames.put(21, "Currency");
		shipNames.put(22, "Civilian");
		shipNames.put(23, "Colonial");
		shipNames.put(24, "Catalys");
		shipNames.put(25, "Campaign");
		shipNames.put(26, "Concept");
		shipNames.put(27, "Charity");
		shipNames.put(28, "Chapman");
		shipNames.put(29, "Crystal");
		shipNames.put(30, "Challenge");
		shipNames.put(31, "Curiosity");
		shipNames.put(32, "Deadline");
		shipNames.put(33, "Devotion");
		shipNames.put(34, "Destiny");
		shipNames.put(35, "Dilligent");
		shipNames.put(36, "Disciplined");
		shipNames.put(37, "Distinctive");
		shipNames.put(38, "Destructive");
		shipNames.put(39, "Descriptive");
		shipNames.put(40, "Determined");
		shipNames.put(41, "Envelope");
		shipNames.put(42, "Emerging");
		shipNames.put(43, "Endeavor");
		shipNames.put(44, "Eloquent");
		shipNames.put(45, "Emergent");
		shipNames.put(46, "Esthetic");
		shipNames.put(47, "Eventful");
		shipNames.put(48, "Elective");
		shipNames.put(49, "Efficient");
		shipNames.put(50, "Encounter");
		shipNames.put(51, "Fundemental");
		shipNames.put(52, "Foundation");
		shipNames.put(53, "Functional");
		shipNames.put(54, "Friendship");
		shipNames.put(55, "Forbidding");
		shipNames.put(56, "Favorable");
		shipNames.put(57, "Fortitude");
		shipNames.put(58, "Forgiving");
		shipNames.put(59, "Footprint");
		shipNames.put(60, "Forgetful");
		shipNames.put(61, "Greatful");
		shipNames.put(62, "Goodwill");
		shipNames.put(63, "Grandeur");
		shipNames.put(64, "Gauntlet");
		shipNames.put(65, "Guardian");
		shipNames.put(66, "Gemstone");
		shipNames.put(67, "Gargoyle");
		shipNames.put(68, "Granite");
		shipNames.put(69, "Getaway");
		shipNames.put(70, "Genuine");
		shipNames.put(71, "Horizon");
		shipNames.put(72, "Holding");
		shipNames.put(73, "Helpful");
		shipNames.put(74, "Honesty");
		shipNames.put(75, "Heroic");
		shipNames.put(76, "Hospitality");
		shipNames.put(77, "Highspeed");
		shipNames.put(78, "Highland");
		shipNames.put(79, "Honorable");
		shipNames.put(80, "Iluminate");
		shipNames.put(81, "Idealistic");
		shipNames.put(82, "Invincible");
		shipNames.put(83, "Identical");
		shipNames.put(84, "Injector");
		shipNames.put(85, "Influencer");
		shipNames.put(86, "Instant");
		shipNames.put(87, "Inventive");
		shipNames.put(88, "Intellect");
		shipNames.put(89, "Imagination");
		shipNames.put(90, "Ingenious");
		shipNames.put(91, "Justice");
		shipNames.put(92, "Justification");
		shipNames.put(93, "Journeyman");
		shipNames.put(94, "Judgement");
		shipNames.put(95, "Judiciary");
		shipNames.put(96, "Joker");
		shipNames.put(97, "Jolly");
		shipNames.put(98, "Knowledge");
		shipNames.put(99, "Kickstart");
		shipNames.put(100, "Kindness");
		shipNames.put(101, "Kingdom");
		shipNames.put(102, "Kestrel");
		shipNames.put(103, "Kerogen");
		shipNames.put(104, "Kinesis");
		shipNames.put(105, "Kinglet");
		shipNames.put(106, "Leadership");
		shipNames.put(107, "Liberty");
		shipNames.put(108, "Limitless");
		shipNames.put(109, "Legendary");
		shipNames.put(110, "Leisurely");
		shipNames.put(111, "Luminous");
		shipNames.put(112, "Loophole");
		shipNames.put(113, "Locomotive");
		shipNames.put(114, "Loyalist");
		shipNames.put(115, "Lifeline");
		shipNames.put(116, "Loyalty");
		shipNames.put(117, "Lineman");
		shipNames.put(118, "Logical");
		shipNames.put(119, "Lottery");
		shipNames.put(120, "Leisurely");
		shipNames.put(121, "Motivation");
		shipNames.put(122, "Monolithic");
		shipNames.put(123, "Missionary");
		shipNames.put(124, "Mercenary");
		shipNames.put(125, "Manifest");
		shipNames.put(126, "Monument");
		shipNames.put(127, "Merchant");
		shipNames.put(128, "Mobility");
		shipNames.put(129, "Majestic");
		shipNames.put(130, "Magician");
		shipNames.put(131, "Neutrality");
		shipNames.put(132, "Navigator");
		shipNames.put(133, "Nobleman");
		shipNames.put(134, "Novelty");
		shipNames.put(135, "Neutral");
		shipNames.put(136, "Narrow");
		shipNames.put(137, "Obscurity");
		shipNames.put(138, "Opportunity");
		shipNames.put(139, "Original");
		shipNames.put(140, "Optimism");
		shipNames.put(141, "Ordinary");
		shipNames.put(142, "Ordinate");
		shipNames.put(143, "Optical");
		shipNames.put(144, "Obscure");
		shipNames.put(145, "Outpost");
		shipNames.put(146, "Obvious");
		shipNames.put(147, "Outlast");
		shipNames.put(148, "Outcast");
		shipNames.put(149, "Overlie");
		shipNames.put(150, "Perseverance");
		shipNames.put(151, "Profitable");
		shipNames.put(152, "Prosperity");
		shipNames.put(153, "Philosophy");
		shipNames.put(154, "Principal");
		shipNames.put(155, "Privilege");
		shipNames.put(156, "Plausible");
		shipNames.put(157, "Priceless");
		shipNames.put(158, "Plentiful");
		shipNames.put(159, "Pursuant");
		shipNames.put(160, "Qualified");
		shipNames.put(161, "Question");
		shipNames.put(162, "Quality");
		shipNames.put(163, "Quality");
		shipNames.put(164, "Quantum");
		shipNames.put(165, "Quartz");
		shipNames.put(166, "Relativity");
		shipNames.put(167, "Respective");
		shipNames.put(168, "Reputable");
		shipNames.put(169, "Resilient");
		shipNames.put(170, "Reverence");
		shipNames.put(171, "Recurrent");
		shipNames.put(172, "Requisite");
		shipNames.put(173, "Replicate");
		shipNames.put(174, "Reghteous");
		shipNames.put(175, "Repurpose");
		shipNames.put(176, "Resurrect");
		shipNames.put(177, "Recovery");
		shipNames.put(178, "Restless");
		shipNames.put(179, "Residual");
		shipNames.put(180, "Rational");
		shipNames.put(181, "Simplicity");
		shipNames.put(182, "Sufficient");
		shipNames.put(183, "Successful");
		shipNames.put(184, "Signature");
		shipNames.put(185, "Suitable");
		shipNames.put(186, "Seamless");
		shipNames.put(187, "Symphony");
		shipNames.put(188, "Symbolic");
		shipNames.put(189, "Scrutiny");
		shipNames.put(190, "Treasury");
		shipNames.put(191, "Truthful");
		shipNames.put(192, "Tenacity");
		shipNames.put(193, "Tranquil");
		shipNames.put(194, "Tireless");
		shipNames.put(195, "Timeless");
		shipNames.put(196, "Truthful");
		shipNames.put(197, "Trivial");
		shipNames.put(198, "Timely");
		shipNames.put(199, "Trophy");
		shipNames.put(200, "Unaffected");
		shipNames.put(201, "Universal");
		shipNames.put(202, "Utility");
		shipNames.put(203, "Undying");
		shipNames.put(204, "Utopian");
		shipNames.put(205, "Utilize");
		shipNames.put(206, "Uniform");
		shipNames.put(207, "Uplift");
		shipNames.put(208, "Upheld");
		shipNames.put(209, "Unique");
		shipNames.put(210, "Unseen");
		shipNames.put(211, "Visualizer");
		shipNames.put(212, "Visibility");
		shipNames.put(213, "Victory");
		shipNames.put(214, "Vitality");
		shipNames.put(215, "Vigilance");
		shipNames.put(216, "Visionary");
		shipNames.put(217, "Valuable");
		shipNames.put(218, "Velocity");
		shipNames.put(219, "Vitality");
		shipNames.put(220, "Worship");
		shipNames.put(221, "Worthy");
		shipNames.put(222, "Welbeing");
		shipNames.put(223, "Willpower");
		shipNames.put(224, "Wholesome");
		shipNames.put(225, "Wildcard");
		shipNames.put(226, "Watchful");
		shipNames.put(227, "Welfare");
		shipNames.put(228, "Willing");
		shipNames.put(229, "Wishful");
		shipNames.put(230, "Xenolith");
		shipNames.put(231, "Xerox");
		shipNames.put(232, "Xerosis");
		shipNames.put(233, "Xenograft");
		shipNames.put(234, "Xenogenic");
		shipNames.put(235, "Xenon");
		shipNames.put(236, "Yearned");
		shipNames.put(237, "Yielding");
		shipNames.put(238, "Youthful");
		shipNames.put(239, "Yearning");
		shipNames.put(240, "Zeppelin");
		shipNames.put(241, "zillion");
		shipNames.put(242, "zipper");
		shipNames.put(243, "zealot");
		
		//joke names
		shipNames.put(244, "Boondoggle");
		shipNames.put(245, "Catastrophe");
		shipNames.put(246, "Tragedy");
		shipNames.put(247, "Wobbly");
		shipNames.put(248, "Shabby");
		shipNames.put(249, "DogeCoin");
		shipNames.put(250, "YOLO");
		shipNames.put(251, "Heartless");
		shipNames.put(252, "GetToTheChoppa");
		shipNames.put(253, "Titanic");
		shipNames.put(254, "Failure");
		shipNames.put(255, "BadIdea");
		shipNames.put(256, "Joke");
		shipNames.put(257, "I'mPickleShip!");
		shipNames.put(258, "We'reGoingDown!");
		shipNames.put(259, "BadTimes");
		shipNames.put(260, "InstantRegret");
		
		//star trek names
		shipNames.put(261, "Archer");
		shipNames.put(262, "Voyager");
		shipNames.put(263, "Enterprise");
		shipNames.put(264, "Narada");
		shipNames.put(265, "Vengeance");
		shipNames.put(266, "Scimitar");
		shipNames.put(267, "Discovery");
		shipNames.put(268, "Defiant");
		shipNames.put(269, "Prometheus");
		shipNames.put(270, "Raven");
	}
	private static void initMaleNames() {
		maleNames.put(0,  "Ash");
		maleNames.put(1,  "Aron");
		maleNames.put(2,  "Ace");
		maleNames.put(3,  "Adam");
		maleNames.put(4,  "Adrian");
		maleNames.put(5,  "Allan");
		maleNames.put(6,  "Alex");
		maleNames.put(7,  "Alfie");
		maleNames.put(8,  "Angus");
		maleNames.put(9,  "Axel");
		maleNames.put(10, "Basil");
		maleNames.put(11, "Bilbo");
		maleNames.put(12, "Brice");
		maleNames.put(13, "Brian");
		maleNames.put(14, "Bruno");
		maleNames.put(15, "Buster");
		maleNames.put(16, "Barry");
		maleNames.put(17, "Blair");
		maleNames.put(18, "Brant");
		maleNames.put(19, "Boyd");
		maleNames.put(20, "Cleb");
		maleNames.put(21, "Carl");
		maleNames.put(22, "Cash");
		maleNames.put(23, "Clark");
		maleNames.put(24, "Cody");
		maleNames.put(25, "Curtis");
		maleNames.put(26, "Chad");
		maleNames.put(27, "Chet");
		maleNames.put(28, "Clif");
		maleNames.put(29, "Chase");
		maleNames.put(30, "Dale");
		maleNames.put(31, "Dan");
		maleNames.put(32, "Donald");
		maleNames.put(33, "Dwight");
		maleNames.put(34, "Dwayne");
		maleNames.put(35, "Dilbert");
		maleNames.put(36, "Dill");
		maleNames.put(37, "David");
		maleNames.put(38, "Dexter");
		maleNames.put(39, "Darius");
		maleNames.put(40, "Earl");
		maleNames.put(41, "Edgar");
		maleNames.put(42, "Edward");
		maleNames.put(43, "Elmer");
		maleNames.put(44, "Evan");
		maleNames.put(45, "Everett");
		maleNames.put(46, "Eric");
		maleNames.put(47, "Ernest");
		maleNames.put(48, "Elliott");
		maleNames.put(49, "Ethan");
		maleNames.put(50, "Fabio");
		maleNames.put(51, "Felix");
		maleNames.put(52, "Finn");
		maleNames.put(53, "Flynn");
		maleNames.put(54, "Forbes");
		maleNames.put(55, "Frank");
		maleNames.put(56, "Fraser");
		maleNames.put(57, "Freeman");
		maleNames.put(58, "Forrest");
		maleNames.put(59, "Francis");
		maleNames.put(60, "Gabriel");
		maleNames.put(61, "Gage");
		maleNames.put(62, "Galen");
		maleNames.put(63, "Gavin");
		maleNames.put(64, "George");
		maleNames.put(65, "Gerald");
		maleNames.put(66, "Glen");
		maleNames.put(67, "Grant");
		maleNames.put(68, "Gray");
		maleNames.put(69, "Greg");
		maleNames.put(70, "Hall");
		maleNames.put(71, "Hamish");
		maleNames.put(72, "Henry");
		maleNames.put(73, "Homer");
		maleNames.put(74, "Hugo");
		maleNames.put(75, "Hunter");
		maleNames.put(76, "Howie");
		maleNames.put(77, "Harold");
		maleNames.put(78, "Hamish");
		maleNames.put(79, "Herbert");
		maleNames.put(80, "Ian");
		maleNames.put(81, "Ichabod");
		maleNames.put(82, "Ignacio");
		maleNames.put(83, "Igor");
		maleNames.put(84, "Indigo");
		maleNames.put(85, "Irwin");
		maleNames.put(86, "Isaac");
		maleNames.put(87, "Irwin");
		maleNames.put(88, "Ingvar");
		maleNames.put(89, "Isaiah");
		maleNames.put(90, "Jack");
		maleNames.put(91, "Jacob");
		maleNames.put(92, "Jarod");
		maleNames.put(93, "Jenson");
		maleNames.put(94, "Jesper");
		maleNames.put(95, "Jett");
		maleNames.put(96, "John");
		maleNames.put(97, "Jonas");
		maleNames.put(98, "Judd");
		maleNames.put(99, "Justin");
		maleNames.put(100, "Kato");
		maleNames.put(101, "Keanu");
		maleNames.put(102, "Keith");
		maleNames.put(103, "Kermit");
		maleNames.put(104, "Kevin");
		maleNames.put(105, "Klaus");
		maleNames.put(106, "Knox");
		maleNames.put(107, "Kyle");
		maleNames.put(108, "Kwame");
		maleNames.put(109, "Ken");
		maleNames.put(110, "Lachy");
		maleNames.put(111, "Lane");
		maleNames.put(112, "Lars");
		maleNames.put(113, "Leo");
		maleNames.put(114, "Liam");
		maleNames.put(115, "Loagn");
		maleNames.put(116, "Louis");
		maleNames.put(117, "Luke");
		maleNames.put(118, "Leroy");
		maleNames.put(119, "Leif");
		maleNames.put(120, "Mark");
		maleNames.put(121, "Mike");
		maleNames.put(122, "Matt");
		maleNames.put(123, "Miles");
		maleNames.put(124, "Mario");
		maleNames.put(125, "Martin");
		maleNames.put(126, "Malcolm");
		maleNames.put(127, "Magnus");
		maleNames.put(128, "Marshall");
		maleNames.put(129, "Ming");
		maleNames.put(130, "Nathan");
		maleNames.put(131, "Nemo");
		maleNames.put(132, "Nestor");
		maleNames.put(133, "Neville");
		maleNames.put(134, "Naom");
		maleNames.put(135, "Norris");
		maleNames.put(136, "Norman");
		maleNames.put(137, "Noor");
		maleNames.put(138, "Nolam");
		maleNames.put(139, "Neelam");
		maleNames.put(140, "Ocavio");
		maleNames.put(141, "Olam");
		maleNames.put(142, "Oliver");
		maleNames.put(143, "Omar");
		maleNames.put(144, "Oreste");
		maleNames.put(145, "Own");
		maleNames.put(146, "Oscar");
		maleNames.put(147, "Ozzy");
		maleNames.put(148, "Olaf");
		maleNames.put(149, "Orange");
		maleNames.put(150, "Pablo");
		maleNames.put(151, "Palmer");
		maleNames.put(152, "Paul");
		maleNames.put(153, "Pavel");
		maleNames.put(154, "Paris");
		maleNames.put(155, "Porter");
		maleNames.put(156, "Peter");
		maleNames.put(157, "Philip");
		maleNames.put(158, "Pierce");
		maleNames.put(159, "Plato");
		maleNames.put(160, "Quincy");
		maleNames.put(161, "Quinn");
		maleNames.put(162, "Quinton");
		maleNames.put(163, "Quinlan");
		maleNames.put(164, "Queen");
		maleNames.put(165, "Qing");
		maleNames.put(166, "Quincy");
		maleNames.put(167, "Quang");
		maleNames.put(168, "Qadir");
		maleNames.put(169, "Qasim");
		maleNames.put(170, "Ragnar");
		maleNames.put(171, "Ralph");
		maleNames.put(172, "Remy");
		maleNames.put(173, "Rhes");
		maleNames.put(174, "Ross");
		maleNames.put(175, "Ryan");
		maleNames.put(176, "Ryder");
		maleNames.put(177, "Rhett");
		maleNames.put(178, "Regis");
		maleNames.put(179, "Raoul");
		maleNames.put(180, "Salman");
		maleNames.put(181, "Samir");
		maleNames.put(182, "Sawyer");
		maleNames.put(183, "Sherwin");
		maleNames.put(184, "Silas");
		maleNames.put(185, "Simon");
		maleNames.put(186, "Steven");
		maleNames.put(187, "Stanley");
		maleNames.put(188, "Stone");
		maleNames.put(189, "Sall");
		maleNames.put(190, "Thor");
		maleNames.put(191, "Trey");
		maleNames.put(192, "Tyrone");
		maleNames.put(193, "Tristan");
		maleNames.put(194, "Thomas");
		maleNames.put(195, "Tom");
		maleNames.put(196, "Tyler");
		maleNames.put(197, "Tim");
		maleNames.put(198, "Trevor");
		maleNames.put(199, "Tone");
		maleNames.put(200, "Ulric");
		maleNames.put(201, "Ulysses");
		maleNames.put(202, "Urban");
		maleNames.put(203, "Umberto");
		maleNames.put(204, "Ultan");
		maleNames.put(205, "Umar");
		maleNames.put(206, "Upton");
		maleNames.put(207, "Uriah");
		maleNames.put(208, "Ulrich");
		maleNames.put(209, "Urving");
		maleNames.put(210, "Vance");
		maleNames.put(211, "Vasek");
		maleNames.put(212, "Vern");
		maleNames.put(213, "Viggo");
		maleNames.put(214, "Victor");
		maleNames.put(215, "Virgil");
		maleNames.put(216, "Vitus");
		maleNames.put(217, "Vishnu");
		maleNames.put(218, "Vaughn");
		maleNames.put(219, "Vasiliy");
		maleNames.put(220, "Waldo");
		maleNames.put(221, "Walter");
		maleNames.put(222, "Warick");
		maleNames.put(223, "Webster");
		maleNames.put(224, "Wiley");
		maleNames.put(225, "William");
		maleNames.put(226, "Willis");
		maleNames.put(227, "Winslow");
		maleNames.put(228, "Wyman");
		maleNames.put(229, "Willard");
		maleNames.put(230, "Xander");
		maleNames.put(231, "Xavier");
		maleNames.put(232, "Xenon");
		maleNames.put(233, "Xerxes");
		maleNames.put(234, "Ximun");
		maleNames.put(235, "Xandros");
		maleNames.put(236, "Xoan");
		maleNames.put(237, "Xeno");
		maleNames.put(238, "Yale");
		maleNames.put(239, "Yuri");
		maleNames.put(240, "Yasir");
		maleNames.put(241, "Yosef");
		maleNames.put(242, "Yorick");
		maleNames.put(243, "Yves");
		maleNames.put(244, "Zach");
		maleNames.put(245, "Zane");
		maleNames.put(246, "Zion");
		maleNames.put(247, "Zebby");
		maleNames.put(247, "Zebulon");
		maleNames.put(249, "Zachary");
		maleNames.put(250, "Ziah");		
	}
	private static void initFemaleNames() {
		femaleNames.put(0,  "Abby");
		femaleNames.put(1,  "Ada");
		femaleNames.put(2,  "Adaline");
		femaleNames.put(3,  "Adele");
		femaleNames.put(4,  "Alana");
		femaleNames.put(5,  "Alice");
		femaleNames.put(6,  "Alexis");
		femaleNames.put(7,  "Allison");
		femaleNames.put(8,  "Alora");
		femaleNames.put(9,  "Amelia");
		femaleNames.put(10, "Bridget");
		femaleNames.put(11, "Bria");
		femaleNames.put(12, "Briella");
		femaleNames.put(13, "Brooke");
		femaleNames.put(14, "Brylee");
		femaleNames.put(15, "Bristol");
		femaleNames.put(16, "Brit");
		femaleNames.put(17, "Brittany");
		femaleNames.put(18, "Bonnie");
		femaleNames.put(19, "Blair");
		femaleNames.put(20, "Cara");
		femaleNames.put(21, "Carmen");
		femaleNames.put(22, "Casey");
		femaleNames.put(23, "Catherine");
		femaleNames.put(24, "Catalina");
		femaleNames.put(25, "Camila");
		femaleNames.put(26, "Callie");
		femaleNames.put(27, "Cameron");
		femaleNames.put(28, "Chanel");
		femaleNames.put(29, "Chloe");
		femaleNames.put(30, "Danna");
		femaleNames.put(31, "Deborah");
		femaleNames.put(32, "Delilah");
		femaleNames.put(33, "Demi");
		femaleNames.put(34, "Diana");
		femaleNames.put(35, "Dorothy");
		femaleNames.put(36, "Dylan");
		femaleNames.put(37, "Dream");
		femaleNames.put(38, "Dixy");
		femaleNames.put(39, "Doris");
		femaleNames.put(40, "Edith");
		femaleNames.put(41, "Elaine");
		femaleNames.put(42, "Eliza");
		femaleNames.put(43, "Ellen");
		femaleNames.put(44, "Eloise");
		femaleNames.put(45, "Elora");
		femaleNames.put(46, "Emelia");
		femaleNames.put(47, "Emilly");
		femaleNames.put(48, "Emma");
		femaleNames.put(49, "Eva");
		femaleNames.put(50, "Faith");
		femaleNames.put(51, "Faye");
		femaleNames.put(52, "Felicity");
		femaleNames.put(53, "Fiona");
		femaleNames.put(54, "Florence");
		femaleNames.put(55, "Frances");
		femaleNames.put(56, "Floral");
		femaleNames.put(57, "Frida");
		femaleNames.put(58, "Foxley");
		femaleNames.put(59, "Frankie");
		femaleNames.put(60, "Gabriela");
		femaleNames.put(61, "Gina");
		femaleNames.put(62, "Giselle");
		femaleNames.put(63, "Gracie");
		femaleNames.put(64, "Greta");
		femaleNames.put(65, "Gwen");
		femaleNames.put(66, "Grace");
		femaleNames.put(67, "Gloria");
		femaleNames.put(68, "Glory");
		femaleNames.put(69, "Goldie");	
		femaleNames.put(70, "Hadlee");
		femaleNames.put(71, "Hailee");
		femaleNames.put(72, "Harley");
		femaleNames.put(73, "Halle");
		femaleNames.put(74, "Hannah");
		femaleNames.put(75, "Harlow");
		femaleNames.put(76, "Harper");
		femaleNames.put(77, "Haylee");
		femaleNames.put(78, "Harmoni");
		femaleNames.put(79, "Hazel");
		femaleNames.put(80, "India");
		femaleNames.put(81, "Irene");
		femaleNames.put(82, "Iris");
		femaleNames.put(83, "Isla");
		femaleNames.put(84, "Ivanna");
		femaleNames.put(85, "Isabel");
		femaleNames.put(86, "Isabela");
		femaleNames.put(87, "Isabelle");
		femaleNames.put(88, "Itzel");
		femaleNames.put(89, "Ingrid");
		femaleNames.put(90, "Jade");
		femaleNames.put(91, "Jada");
		femaleNames.put(92, "Jamie");
		femaleNames.put(93, "Jane");
		femaleNames.put(94, "Janelle");
		femaleNames.put(95, "Jaycee");
		femaleNames.put(96, "Jayda");
		femaleNames.put(97, "Jaylee");
		femaleNames.put(98, "Joyce");
		femaleNames.put(99, "Joanne");
		femaleNames.put(100, "Kai");
		femaleNames.put(101, "Kailey");
		femaleNames.put(102, "Kaiya");
		femaleNames.put(103, "Kali");
		femaleNames.put(104, "Kate");
		femaleNames.put(105, "Karen");
		femaleNames.put(106, "Kara");
		femaleNames.put(107, "Karla");
		femaleNames.put(108, "Kassidy");
		femaleNames.put(109, "kaylee");
		femaleNames.put(110, "Lacey");
		femaleNames.put(111, "Leah");
		femaleNames.put(112, "Lana");
		femaleNames.put(113, "Lora");
		femaleNames.put(114, "Leia");
		femaleNames.put(115, "Leyla");
		femaleNames.put(116, "Lola");
		femaleNames.put(117, "Lila");
		femaleNames.put(118, "Laura");
		femaleNames.put(119, "Lauren");
		femaleNames.put(120, "Mabel");
		femaleNames.put(121, "Macy");
		femaleNames.put(122, "Maddison");
		femaleNames.put(123, "Maisie");
		femaleNames.put(124, "Malia");
		femaleNames.put(125, "Margo");
		femaleNames.put(126, "Marie");
		femaleNames.put(127, "Mary");
		femaleNames.put(128, "Mavis");
		femaleNames.put(129, "Maxine");
		femaleNames.put(130, "Nala");
		femaleNames.put(131, "Nancy");
		femaleNames.put(132, "Natalie");
		femaleNames.put(133, "Nola");
		femaleNames.put(134, "Nova");
		femaleNames.put(135, "Nicole");
		femaleNames.put(136, "Nina");
		femaleNames.put(137, "Norah");
		femaleNames.put(138, "Novah");
		femaleNames.put(139, "Naya");
		femaleNames.put(140, "Oaklee");
		femaleNames.put(141, "Oakley");
		femaleNames.put(142, "Oaklynn");
		femaleNames.put(143, "Octavia");
		femaleNames.put(144, "Olive");
		femaleNames.put(145, "Olivia");
		femaleNames.put(146, "Opal");
		femaleNames.put(147, "Ophelia");
		femaleNames.put(148, "Oregon");
		femaleNames.put(149, "Opal");
		femaleNames.put(150, "Paige");
		femaleNames.put(151, "Palmer");
		femaleNames.put(152, "Penny");
		femaleNames.put(153, "Piper");
		femaleNames.put(154, "Pearl");
		femaleNames.put(155, "Poppy");
		femaleNames.put(156, "Paris");
		femaleNames.put(157, "Payton");
		femaleNames.put(158, "Promise");
		femaleNames.put(159, "Paula");
		femaleNames.put(160, "Queen");
		femaleNames.put(161, "Quinn");
		femaleNames.put(162, "Quincey");
		femaleNames.put(163, "Rachel");
		femaleNames.put(164, "Rikky");
		femaleNames.put(165, "Rain");
		femaleNames.put(166, "Rayna");
		femaleNames.put(167, "Raven");
		femaleNames.put(168, "Ragen");
		femaleNames.put(169, "Robin");
		femaleNames.put(170, "Sabrina");
		femaleNames.put(171, "Sadie");
		femaleNames.put(172, "Saige");
		femaleNames.put(173, "Salma");
		femaleNames.put(174, "Sammy");
		femaleNames.put(175, "Sara");
		femaleNames.put(176, "Sarah");
		femaleNames.put(177, "Sasha");
		femaleNames.put(178, "Stella");
		femaleNames.put(179, "Selena");
		femaleNames.put(180, "Sky");
		femaleNames.put(181, "Talia");
		femaleNames.put(182, "Tatum");
		femaleNames.put(183, "Taylor");
		femaleNames.put(184, "Teagan");
		femaleNames.put(185, "Tenley");
		femaleNames.put(186, "Tessa");
		femaleNames.put(187, "Tiana");
		femaleNames.put(188, "Trinity");
		femaleNames.put(189, "Tiffany");
		femaleNames.put(190, "Undine");
		femaleNames.put(191, "Ursula");
		femaleNames.put(192, "Uma");
		femaleNames.put(193, "Valery");
		femaleNames.put(194, "Veronica");
		femaleNames.put(195, "Vivian");
		femaleNames.put(196, "Violet");
		femaleNames.put(197, "Vera");
		femaleNames.put(198, "Vienna");
		femaleNames.put(199, "Victoria");
		femaleNames.put(200, "Wendy");
		femaleNames.put(201, "Waverly");
		femaleNames.put(202, "Willa");
		femaleNames.put(203, "Willow");
		femaleNames.put(204, "Winter");
		femaleNames.put(205, "Wren");
		femaleNames.put(206, "Ximena");
		femaleNames.put(207, "Xina");
		femaleNames.put(208, "Xola");
		femaleNames.put(209, "Yara");
		femaleNames.put(210, "Yevone");
		femaleNames.put(211, "Zahra");
		femaleNames.put(212, "Zelda");
		femaleNames.put(213, "Zaina");
	}
	private static void initLastNames() {
		lastNames.put(0, "Acre");
		lastNames.put(1, "Addams");
		lastNames.put(2, "Atkins");
		lastNames.put(3, "Anderson");
		lastNames.put(4, "Allen");
		lastNames.put(5, "Adler");
		lastNames.put(6, "Amber");
		lastNames.put(7, "Baker");
		lastNames.put(8, "Bell");
		lastNames.put(9, "Brown");
		lastNames.put(10, "Bennett");
		lastNames.put(11, "Burns");
		lastNames.put(12, "Campbell");
		lastNames.put(13, "Carter");
		lastNames.put(14, "Clark");
		lastNames.put(15, "Collins");
		lastNames.put(16, "Cook");
		lastNames.put(17, "Cooper");
		lastNames.put(18, "Davidson");
		lastNames.put(19, "Dawson");
		lastNames.put(20, "Evans");
		lastNames.put(21, "Easmon");
		lastNames.put(22, "Edmend");
		lastNames.put(23, "Ellis");
		lastNames.put(24, "Edwards");
		lastNames.put(25, "Eady");
		lastNames.put(26, "Fleming");
		lastNames.put(27, "Forester");
		lastNames.put(28, "Foster");
		lastNames.put(29, "Fox");
		lastNames.put(30, "Gardner");
		lastNames.put(31, "Gibbs");
		lastNames.put(32, "Gray");
		lastNames.put(33, "Green");
		lastNames.put(34, "Goyle");
		lastNames.put(35, "Hall");
		lastNames.put(36, "Hamilton");
		lastNames.put(37, "Haris");
		lastNames.put(38, "Henderson");
		lastNames.put(39, "Homes");
		lastNames.put(40, "Hughes");
		lastNames.put(41, "Jackson");
		lastNames.put(42, "Johnson");
		lastNames.put(43, "Jones");
		lastNames.put(44, "Jameson");
		lastNames.put(45, "Jenkins");
		lastNames.put(46, "Jailer");
		lastNames.put(47, "Kelly");
		lastNames.put(48, "King");
		lastNames.put(49, "Kramer");
		lastNames.put(50, "Krall");
		lastNames.put(51, "Kong");
		lastNames.put(52, "Kerry");
		lastNames.put(53, "Knife");
		lastNames.put(54, "Kettle");
		lastNames.put(55, "Karington");
		lastNames.put(56, "Kolly");
		lastNames.put(57, "Lee");
		lastNames.put(58, "Lewis");
		lastNames.put(59, "Luster");
		lastNames.put(60, "Lane");
		lastNames.put(61, "Lynch");
		lastNames.put(62, "Lawson");
		lastNames.put(63, "Levy");
		lastNames.put(64, "Lender");
		lastNames.put(65, "Lonsly");
		lastNames.put(66, "Lock");
		lastNames.put(67, "Miller");
		lastNames.put(68, "Marshal");
		lastNames.put(69, "McDonald");
		lastNames.put(70, "Moore");
		lastNames.put(71, "Murray");
		lastNames.put(72, "Murphy");
		lastNames.put(73, "Mathias");
		lastNames.put(74, "Nicholson");
		lastNames.put(75, "Newman");
		lastNames.put(76, "Neal");
		lastNames.put(77, "Norton");
		lastNames.put(78, "Nash");
		lastNames.put(79, "Noble");
		lastNames.put(80, "Norman");
		lastNames.put(81, "Osborne");
		lastNames.put(82, "Olson");
		lastNames.put(83, "O'Brien");
		lastNames.put(84, "Owens");
		lastNames.put(85, "Oritz");
		lastNames.put(86, "Parker");
		lastNames.put(87, "Peterson");
		lastNames.put(88, "Phillips");
		lastNames.put(89, "Porter");
		lastNames.put(90, "Potter");
		lastNames.put(91, "Powell");
		lastNames.put(92, "Reed");
		lastNames.put(93, "Reid");
		lastNames.put(94, "Richards");
		lastNames.put(95, "Richardson");
		lastNames.put(96, "Robers");
		lastNames.put(97, "Russel");
		lastNames.put(98, "Sanders");
		lastNames.put(99, "Scott");
		lastNames.put(100, "Shaw");
		lastNames.put(101, "Simpson");
		lastNames.put(102, "Smith");
		lastNames.put(103, "Summers");
		lastNames.put(104, "Snyder");
		lastNames.put(105, "Turner");
		lastNames.put(106, "Teller");
		lastNames.put(107, "Tiler");
		lastNames.put(108, "Taylor");
		lastNames.put(109, "Teal");
		lastNames.put(110, "Ubel");
		lastNames.put(111, "Uber");
		lastNames.put(112, "Ubben");
		lastNames.put(113, "Ubl");
		lastNames.put(114, "Ubel");
		lastNames.put(115, "Uhle");
		lastNames.put(116, "Underle");
		lastNames.put(117, "Uster");
		lastNames.put(118, "Ubel");
		lastNames.put(119, "Vacca");
		lastNames.put(120, "Vacek");
		lastNames.put(121, "Vaden");
		lastNames.put(122, "Vacura");
		lastNames.put(123, "Vagel");
		lastNames.put(124, "Vahl");
		lastNames.put(125, "Vaile");
		lastNames.put(126, "White");
		lastNames.put(127, "Wilson");
		lastNames.put(128, "Wood");
		lastNames.put(129, "Wright");
		lastNames.put(130, "Whaler");
		lastNames.put(131, "Young");
		lastNames.put(132, "York");
		lastNames.put(133, "Yates");
		lastNames.put(134, "Yoink");
		lastNames.put(135, "Zafar");
		lastNames.put(136, "Zaldi");
		lastNames.put(137, "Zakar");
		lastNames.put(138, "Zalt");
		lastNames.put(139, "Zale");
		lastNames.put(140, "Zerg");
	}
	private static void initRoot() {
		initRootA();
		initRootB();
		initRootC();
		initRootD();
		initRootE();
		initRootF();
		initRootG();
		initRootH();
		initRootI();
		initRootJ();
		initRootK();
		initRootL();
		initRootM();
		initRootN();
		initRootO();
		initRootP();
		initRootQ();
		initRootR();
		initRootS();
		initRootT();
		initRootU();
		initRootV();
		initRootW();
		initRootX();
		initRootY();
		initRootZ();
	}	
	private static void initRootA() {
		rootA.put(0, "acro");
		rootA.put(1, "act");
		rootA.put(2, "agr");
		rootA.put(3, "aer");
		rootA.put(4, "alg");
		rootA.put(5, "ambi");
		rootA.put(6, "ami");
		rootA.put(7, "ana");
		rootA.put(8, "andr");
		rootA.put(9, "anim");
		rootA.put(10, "ann");
		rootA.put(11, "ante");
		rootA.put(12, "anth");
		rootA.put(13, "anti");
		rootA.put(14, "apo");
		rootA.put(15, "aqu");
		rootA.put(16, "arbo");
		rootA.put(17, "arch");
		rootA.put(18, "arth");
		rootA.put(19, "art");
		rootA.put(20, "astr");
		rootA.put(21, "auto");
		rootA.put(22, "avi");
	}
	private static void initRootB() {
		rootB.put(0, "bar");
		rootB.put(1, "bell");
		rootB.put(2, "bene");
		rootB.put(3, "bi");
		rootB.put(4, "bio");
		rootB.put(5, "blas");
		rootB.put(6, "burs");
		rootB.put(7, "bibl");	
		rootB.put(8, "blo");
		rootB.put(9, "ber");
		rootB.put(10, "bos");
		rootB.put(11, "bas");
		rootB.put(12, "bal");
		rootB.put(13, "bok");
		rootB.put(14, "bon");	
	}
	private static void initRootC() {
		rootC.put(0, "calc");
		rootC.put(1, "cand");
		rootC.put(2, "cept");
		rootC.put(3, "card");
		rootC.put(4, "carn");
		rootC.put(5, "cata");
		rootC.put(6, "caut");
		rootC.put(7, "cess");
		rootC.put(8, "cele");
		rootC.put(9, "ceph");
		rootC.put(10, "cere");
		rootC.put(11, "cert");
		rootC.put(12, "chrom");
		rootC.put(13, "chros");
		rootC.put(14, "chrys");
		rootC.put(15, "cide");
		rootC.put(16, "carn");
		rootC.put(17, "clam");
		rootC.put(18, "clar");
		rootC.put(19, "clud");
		rootC.put(20, "clus");
		rootC.put(21, "co");
		rootC.put(22, "com");
		rootC.put(23, "con");
		rootC.put(24, "corp");
		rootC.put(25, "cosm");
		rootC.put(26, "cou");
		rootC.put(27, "cran");
		rootC.put(28, "cred");
		rootC.put(29, "cruc");
		rootC.put(30, "cryp");
		rootC.put(31, "cumu");
		rootC.put(32, "curr");
		rootC.put(33, "cycl");
	}
	private static void initRootD() {
		rootD.put(0, "de");
		rootD.put(1, "deci");
		rootD.put(2, "dem");
		rootD.put(3, "demi");
		rootD.put(4, "dend");
		rootD.put(5, "dent");
		rootD.put(6, "don");
		rootD.put(7, "derm");
		rootD.put(8, "di");
		rootD.put(9, "dia");
		rootD.put(10, "dict");
		rootD.put(11, "domi");
		rootD.put(12, "duc");
		rootD.put(13, "du");
		rootD.put(14, "dur");
		rootD.put(15, "dyn");
		rootD.put(16, "dys");
	}
	private static void initRootE() {
		rootE.put(0, "eg");
		rootE.put(1, "ego");
		rootE.put(2, "endo");
		rootE.put(3, "enn");
		rootE.put(4, "equ");
		rootE.put(5, "erg");
		rootE.put(6, "esth");
		rootE.put(7, "ethn");
		rootE.put(8, "eu");
		rootE.put(9, "ex");
		rootE.put(10, "extr");
		rootE.put(11, "ef");
		rootE.put(12, "erl");
		rootE.put(13, "ent");
		rootE.put(14, "est");
		rootE.put(15, "ers");
	}
	private static void initRootF() {
		rootF.put(0, "fac");
		rootF.put(1, "fer");
		rootF.put(2, "fid");
		rootF.put(3, "flec");
		rootF.put(4, "flor");
		rootF.put(5, "fleu");
		rootF.put(6, "fore");
		rootF.put(7, "form");
		rootF.put(8, "frac");
		rootF.put(9, "fug");
		rootF.put(10, "fus");
	}
	private static void initRootG() {
		rootG.put(0, "gas");
		rootG.put(1, "gen");
		rootG.put(2, "geo");
		rootG.put(3, "ger");
		rootG.put(4, "giga");
		rootG.put(5, "gon");
		rootG.put(6, "gram");
		rootG.put(7, "grap");
		rootG.put(8, "grat");
		rootG.put(9, "gyn");
		rootG.put(10, "gres");
	}
	private static void initRootH() {
		rootH.put(0, "hect");
		rootH.put(1, "heli");
		rootH.put(2, "hel");
		rootH.put(3, "hemi");
		rootH.put(4, "hem");
		rootH.put(5, "hepa");
		rootH.put(6, "hept");
		rootH.put(7, "herb");
		rootH.put(8, "hete");
		rootH.put(9, "hex");
		rootH.put(10, "hist");
		rootH.put(11, "homo");
		rootH.put(12, "hydr");
		rootH.put(13, "hype");
	}
	private static void initRootI() {
		rootI.put(0, "iatr");
		rootI.put(1, "icon");
		rootI.put(2, "idio");
		rootI.put(3, "il");
		rootI.put(4, "in");
		rootI.put(5, "im");
		rootI.put(6, "ir");
		rootI.put(7, "imag");
		rootI.put(8, "infr");
		rootI.put(9, "inte");
		rootI.put(10, "intr");
		rootI.put(11, "ir");
		rootI.put(12, "iso");
	}
	private static void initRootJ() {
		rootJ.put(0, "ject");
		rootJ.put(1, "jud");
		rootJ.put(2, "junc");
		rootJ.put(3, "juv");	
		rootJ.put(4, "jus");
		rootJ.put(5, "jos");
		rootJ.put(6, "jen");
		rootJ.put(7, "jaf");
		rootJ.put(8, "jep");
		rootJ.put(9, "jil");
		rootJ.put(10, "jas");
		rootJ.put(11, "jen");	
	}
	private static void initRootK() {
		rootK.put(0, "kil");
		rootK.put(1, "kin");
		rootK.put(2, "ken");
		rootK.put(3, "kor");
		rootK.put(4, "kel");
		rootK.put(5, "kas");
		rootK.put(6, "kon");
		rootK.put(7, "kan");
		rootK.put(8, "kam");
		rootK.put(9, "kal");
	}
	private static void initRootL() {
		rootL.put(0, "lab");
		rootL.put(1, "lact");
		rootL.put(2, "lat");
		rootL.put(3, "lex");
		rootL.put(4, "leuc");
		rootL.put(5, "lip");
		rootL.put(6, "log");
		rootL.put(7, "luc");
		rootL.put(8, "lud");
		rootL.put(9, "lumi");
		rootL.put(10, "libe");
		rootL.put(11, "lun");		
	}
	private static void initRootM() {
		rootM.put(0, "macr");
		rootM.put(1, "magn");
		rootM.put(2, "mal");
		rootM.put(3, "man");
		rootM.put(4, "mand");
		rootM.put(5, "mani");
		rootM.put(6, "mate");
		rootM.put(7, "max");
		rootM.put(8, "medi");
		rootM.put(9, "mega");
		rootM.put(10, "mela");
		rootM.put(11, "memo");
		rootM.put(12, "merg");
		rootM.put(13, "mers");
		rootM.put(14, "meso");
		rootM.put(15, "meta");
		rootM.put(16, "mete");
		rootM.put(17, "micr");
		rootM.put(18, "mid");
		rootM.put(19, "migr");
		rootM.put(20, "mill");
		rootM.put(21, "min");
		rootM.put(22, "mis");
		rootM.put(23, "mob");
		rootM.put(24, "mon");
		rootM.put(25, "mot");
		rootM.put(26, "morp");
		rootM.put(27, "mort");
		rootM.put(28, "mult");
		rootM.put(29, "mut");
	}
	private static void initRootN() {
		rootN.put(0, "narr");
		rootN.put(1, "nat");
		rootN.put(2, "nav");
		rootN.put(3, "necr");
		rootN.put(4, "neg");
		rootN.put(5, "neo");
		rootN.put(6, "neph");
		rootN.put(7, "neur");
		rootN.put(8, "nom");
		rootN.put(9, "non");
		rootN.put(10, "not");
		rootN.put(11, "noun");
		rootN.put(12, "nov");
		rootN.put(13, "nume");
	}
	private static void initRootO() {
		rootO.put(0, "ob");
		rootO.put(1, "oct");
		rootO.put(2, "ocu");
		rootO.put(3, "od");
		rootO.put(4, "odor");
		rootO.put(5, "omni");
		rootO.put(6, "op");
		rootO.put(7, "opt");
		rootO.put(8, "orth");
		rootO.put(9, "oste");
		rootO.put(10, "out");
		rootO.put(11, "over");
		rootO.put(12, "oxi");
	}
	private static void initRootP() {
		rootP.put(0, "pale");
		rootP.put(1, "pan");
		rootP.put(2, "para");
		rootP.put(3, "path");
		rootP.put(4, "ped");
		rootP.put(5, "pel");
		rootP.put(6, "pent");
		rootP.put(7, "pept");
		rootP.put(8, "per");
		rootP.put(9, "peri");
		rootP.put(10, "phag");
		rootP.put(11, "phil");
		rootP.put(12, "phon");
		rootP.put(13, "phot");
		rootP.put(14, "phyl");
		rootP.put(15, "phys");
		rootP.put(16, "phyt");
		rootP.put(17, "plas");
		rootP.put(18, "plod");
		rootP.put(19, "pneu");
		rootP.put(20, "pod");
		rootP.put(21, "poli");
		rootP.put(22, "poly");
		rootP.put(23, "pon");
		rootP.put(24, "pop");
		rootP.put(25, "port");
		rootP.put(26, "pos");
		rootP.put(27, "pre");
		rootP.put(28, "pro");
		rootP.put(29, "prot");
		rootP.put(30, "psyc");
		rootP.put(31, "pung");
		rootP.put(32, "purg");
		rootP.put(33, "put");
	}
	private static void initRootQ() {
		rootQ.put(0, "quad");
		rootQ.put(1, "quar");
		rootQ.put(2, "quin");	
		rootQ.put(3, "quer");	
		rootQ.put(4, "quil");
		rootQ.put(5, "quaz");
		rootQ.put(6, "quel");	
	}
	private static void initRootR() {
		rootR.put(0, "radi");
		rootR.put(1, "ram");
		rootR.put(2, "re");
		rootR.put(3, "reg");
		rootR.put(4, "retr");
		rootR.put(5, "rhin");
		rootR.put(6, "rhod");
		rootR.put(7, "rid");
		rootR.put(8, "rub");
		rootR.put(9, "rupt");
	}
	private static void initRootS() {
		rootS.put(0, "san");
		rootS.put(1, "scen");
		rootS.put(2, "sci");
		rootS.put(3, "scle");
		rootS.put(4, "scop");
		rootS.put(5, "scri");
		rootS.put(6, "se");
		rootS.put(7, "sect");
		rootS.put(8, "sed");
		rootS.put(9, "sid");
		rootS.put(10, "sess");
		rootS.put(11, "self");
		rootS.put(12, "semi");
		rootS.put(13, "sept");
		rootS.put(14, "serv");
		rootS.put(15, "sex");
		rootS.put(16, "sol");
		rootS.put(17, "somn");
		rootS.put(18, "soph");
		rootS.put(19, "spec");
		rootS.put(20, "spir");
		rootS.put(21, "sta");
		rootS.put(22, "stel");
		rootS.put(23, "stru");
		rootS.put(24, "sub");
		rootS.put(25, "sum");
		rootS.put(26, "sup");
	}
	private static void initRootT() {
		rootT.put(0, "tact");
		rootT.put(1, "tang");
		rootT.put(2, "tax");
		rootT.put(3, "tech");
		rootT.put(4, "tel");
		rootT.put(5, "temp");
		rootT.put(6, "ten");
		rootT.put(7, "tin");
		rootT.put(8, "ter");
		rootT.put(9, "term");
		rootT.put(10, "terr");
		rootT.put(11, "tetr");
		rootT.put(12, "the");
		rootT.put(13, "ther");
		rootT.put(14, "tort");
		rootT.put(15, "tox");
		rootT.put(16, "trac");
		rootT.put(17, "tri");
		rootT.put(18, "tran");
	}
	private static void initRootU() {
		rootU.put(0, "un");
		rootU.put(1, "uni");
		rootU.put(2, "urb");
		rootU.put(3, "ur");
		rootU.put(4, "ul");
	}
	private static void initRootV() {
		rootV.put(0, "vac");
		rootV.put(1, "ven");
		rootV.put(2, "ver");
		rootV.put(3, "verb");
		rootV.put(4, "vers");
		rootV.put(5, "vice");
		rootV.put(6, "vid");
		rootV.put(7, "vic");
		rootV.put(8, "vid");
		rootV.put(9, "vis");
		rootV.put(10, "vol");
		rootV.put(11, "voc");
		rootV.put(12, "vour");
	}
	private static void initRootW() {
		rootW.put(0, "wer");
		rootW.put(1, "wel");
		rootW.put(2, "was");
		rootW.put(3, "wen");
		rootW.put(4, "wan");
		rootW.put(5, "war");
		rootW.put(6, "wor");
		rootW.put(7, "wal");
		rootW.put(8, "wir");
		rootW.put(9, "wed");
		rootW.put(10, "wen");
		rootW.put(11, "wil");
		rootW.put(12, "wis");
		rootW.put(13, "win");
	}
	private static void initRootX() {
		rootX.put(0, "xen");
		rootX.put(1, "xer");
		rootX.put(2, "xor");
		rootX.put(3, "xan");
	}
	private static void initRootY() {
		rootY.put(0, "ye");
		rootX.put(1, "yen");
		rootX.put(2, "yam");
		rootX.put(3, "yo");
		rootX.put(4, "ya");
		rootX.put(5, "yan");
		rootX.put(6, "yon");
		rootX.put(7, "yer");
	}
	private static void initRootZ() {
		rootZ.put(0, "zo");
		rootZ.put(1, "zon");
		rootZ.put(2, "zol");
		rootZ.put(3, "za");
		rootZ.put(4, "zan");
		rootZ.put(5, "zal");
		rootZ.put(6, "ze");
		rootZ.put(7, "zen");
		rootZ.put(8, "zel");
		rootZ.put(9, "zep");
		rootZ.put(10, "zect");
		rootZ.put(11, "zil");
		rootZ.put(12, "zi");
	}
	private static void initSuffix() {
		suffix.put(0, "able");
		suffix.put(1, "al");
		suffix.put(2, "er");
		suffix.put(3, "est");
		suffix.put(4, "ful");
		suffix.put(5, "ible");
		suffix.put(6, "ily");
		suffix.put(7, "ing");
		suffix.put(8, "less");
		suffix.put(9, "ly");
		suffix.put(10, "ness");
		suffix.put(11, "y");		
	}
	private static void initPrefix() {
		prefix.put(0, "de");
		prefix.put(1, "dis");
		prefix.put(2, "ex");
		prefix.put(3, "il");
		prefix.put(4, "im");
		prefix.put(5, "in");
		prefix.put(6, "mis");
		prefix.put(7, "non");
		prefix.put(8, "pre");
		prefix.put(9, "pro");
		prefix.put(10, "re");
		prefix.put(11, "un");		
	}
	
}
