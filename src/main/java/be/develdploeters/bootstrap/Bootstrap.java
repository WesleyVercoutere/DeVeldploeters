package be.develdploeters.bootstrap;

import be.develdploeters.domain.*;
import be.develdploeters.repository.*;
import be.develdploeters.security.AuthoritiesConstants;
import be.develdploeters.service.ActivityService;
import be.develdploeters.service.activity.impl.TourService;
import be.develdploeters.service.dto.ActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("dev")
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private BannerImageRepository bannerImageRepository;
    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private ICERepository iceRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TourService tourService;
    @Autowired
    private ActivityService activityService;


    @Override
    public void run(String... args) throws Exception {
        loadAuthorities();
        loadUsers();
        loadICE();
        loadBannerImages();
        loadSponsors();
        loadEvents();
        loadTours();
    }

    private void loadAuthorities() {

        Authority auth1 = new Authority();
        auth1.setName("ROLE_ADMIN");

        Authority auth2 = new Authority();
        auth2.setName("ROLE_MEMBER");

        Authority auth3 = new Authority();
        auth3.setName("ROLE_BOARD");

        Authority auth4 = new Authority();
        auth4.setName("ROLE_ANONYMOUS");

        authorityRepository.save(auth1);
        authorityRepository.save(auth2);
        authorityRepository.save(auth3);
        authorityRepository.save(auth4);

    }

    private void loadUsers() {
        String passwordAdmin = passwordEncoder.encode("admin");
        String passwordBoard = passwordEncoder.encode("board");
        String passwordMember = passwordEncoder.encode("member");

        List<Authority> authorities = authorityRepository.findAll();

        Set<Authority> member = new HashSet<>();
        member.add(authorities.stream().filter(e -> e.getName().equals(AuthoritiesConstants.MEMBER)).findAny().get());

        Set<Authority> admin = new HashSet<>();
        admin.add(authorities.stream().filter(e -> e.getName().equals(AuthoritiesConstants.MEMBER)).findAny().get());
        admin.add(authorities.stream().filter(e -> e.getName().equals(AuthoritiesConstants.ADMIN)).findAny().get());

        Set<Authority> board = new HashSet<>();
        board.add(authorities.stream().filter(e -> e.getName().equals(AuthoritiesConstants.MEMBER)).findAny().get());
        board.add(authorities.stream().filter(e -> e.getName().equals(AuthoritiesConstants.BOARD)).findAny().get());

        User user1 = new User();
        user1.setLogin("system");
        user1.setPassword(passwordAdmin);
        user1.setFirstName("System");
        user1.setLastName("System");
        user1.setEmail("system@localhost");
        user1.setImageUrl("");
        user1.setActivated(true);
        user1.setLangKey("en");
        user1.setCreatedBy("system");
        user1.setLastModifiedBy("system");
        user1.setAuthorities(admin);

        User user2 = new User();
        user2.setLogin("anonymoususer");
        user2.setPassword(passwordAdmin);
        user2.setFirstName("Anonymous");
        user2.setLastName("User");
        user2.setEmail("anonymous@localhost");
        user2.setImageUrl("");
        user2.setActivated(true);
        user2.setLangKey("en");
        user2.setCreatedBy("system");
        user2.setLastModifiedBy("system");

        User adminUser = new User();
        adminUser.setLogin("admin");
        adminUser.setPassword(passwordAdmin);
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Admin");
        adminUser.setEmail("admin@localhost");
        adminUser.setImageUrl("");
        adminUser.setActivated(true);
        adminUser.setLangKey("en");
        adminUser.setCreatedBy("system");
        adminUser.setLastModifiedBy("system");
        adminUser.setAuthorities(admin);

        User boardUser = new User();
        boardUser.setLogin("board");
        boardUser.setPassword(passwordBoard);
        boardUser.setFirstName("Board");
        boardUser.setLastName("Board");
        boardUser.setEmail("board@localhost");
        boardUser.setImageUrl("");
        boardUser.setActivated(true);
        boardUser.setLangKey("en");
        boardUser.setCreatedBy("system");
        boardUser.setLastModifiedBy("system");
        boardUser.setAuthorities(board);

        User memberUser = new User();
        memberUser.setLogin("member");
        memberUser.setPassword(passwordMember);
        memberUser.setFirstName("Member");
        memberUser.setLastName("Member");
        memberUser.setEmail("member@localhost");
        memberUser.setImageUrl("");
        memberUser.setActivated(true);
        memberUser.setLangKey("en");
        memberUser.setCreatedBy("system");
        memberUser.setLastModifiedBy("system");
        memberUser.setAuthorities(member);

        User user3 = new User();
        user3.setLogin("wesleyvercoutere");
        user3.setPassword(passwordAdmin);
        user3.setFirstName("Wesley");
        user3.setLastName("Vercoutere");
        user3.setEmail("wesley.vercoutere@gmail.com");
        user3.setImageUrl("");
        user3.setActivated(true);
        user3.setLangKey("en");
        user3.setCreatedBy("system");
        user3.setLastModifiedBy("system");
        user3.setAuthorities(admin);
        user3.setAddress(getAddress());

        User user4 = new User();
        user4.setLogin("vanessa.deletter@gmail.com");
        user4.setPassword(passwordMember);
        user4.setFirstName("Vanessa");
        user4.setLastName("De Letter");
        user4.setEmail("vanessa.deletter@gmail.com");
        user4.setImageUrl("");
        user4.setActivated(true);
        user4.setLangKey("en");
        user4.setCreatedBy("system");
        user4.setLastModifiedBy("system");
        user4.setAuthorities(member);

        User user5 = new User();
        user5.setLogin("cleo.vandamme@gmail.com");
        user5.setPassword(passwordMember);
        user5.setFirstName("Cleo");
        user5.setLastName("Van Damme");
        user5.setEmail("cleo.vandamme@gmail.com");
        user5.setImageUrl("");
        user5.setActivated(true);
        user5.setLangKey("en");
        user5.setCreatedBy("system");
        user5.setLastModifiedBy("system");
        user5.setAuthorities(member);

        User user6 = new User();
        user6.setLogin("rita.dewitte@gmail.com");
        user6.setPassword(passwordMember);
        user6.setFirstName("Rita");
        user6.setLastName("De Witte");
        user6.setEmail("rita.dewitte@gmail.com");
        user6.setImageUrl("");
        user6.setActivated(false);
        user6.setLangKey("en");
        user6.setCreatedBy("system");
        user6.setLastModifiedBy("system");
        user6.setAuthorities(member);


//        userRepository.save(user1);
//        userRepository.save(user2);
        userRepository.save(adminUser);
        userRepository.save(boardUser);
        userRepository.save(memberUser);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
    }

    private Address getAddress() {
        Address address = new Address();
        address.setStreet("Kampioenenstraat");
        address.setNumber("1");
        address.setZipCode("9160");
        address.setCity("Lokeren");

        addressRepository.save(address);

        return address;
    }

    private void loadICE() {
        userRepository.findOneByLogin("wesleyvercoutere")
                .ifPresent(user -> {

                ICE ice1 = new ICE();
                ice1.setFirstName("Rita");
                ice1.setLastName("De Witte");
                ice1.setPhone("01/23.45.67");
                ice1.setUser(user);

                ICE ice2 = new ICE();
                ice2.setFirstName("Vanessa");
                ice2.setLastName("De Letter");
                ice2.setPhone("08/91 23 45");
                ice2.setUser(user);

                iceRepository.save(ice1);
                iceRepository.save(ice2);
            });
    }

    private void loadBannerImages() {
        String folder = "banner";

        bannerImageRepository.save(constructBannerImage(true, folder, "clubfoto_banner.jpg", "jpg", "image/jpeg"));
        bannerImageRepository.save(constructBannerImage(true, folder, "foto1.jpg", "jpg", "image/jpeg"));
        bannerImageRepository.save(constructBannerImage(true, folder, "foto2.jpg", "jpg", "image/jpeg"));
        bannerImageRepository.save(constructBannerImage(true, folder, "foto3.jpg", "jpg", "image/jpeg"));
        bannerImageRepository.save(constructBannerImage(false, folder, "foto4.jpg", "jpg", "image/jpeg"));
        bannerImageRepository.save(constructBannerImage(false, folder, "foto5.jpg", "jpg", "image/jpeg"));
        bannerImageRepository.save(constructBannerImage(false, folder, "foto6.png", "png", "image/png"));
        bannerImageRepository.save(constructBannerImage(false, folder, "foto7.png", "png", "image/png"));
    }

    private BannerImage constructBannerImage(boolean active, String folder, String fileName, String extension, String contentType) {
        BannerImage image = new BannerImage();
        image.setActive(active);
        image.setImage(getByteArray(folder, fileName, extension));
        image.setImageContentType(contentType);

        return image;
    }

    private void loadSponsors() {
        sponsorRepository.save(constructSponsor("Donny", "sponsors", "Donny", "png", "image/png", "www.opdorp-donny.be/"));
        sponsorRepository.save(constructSponsor("Van Bocxlaer", "sponsors", "Schilderwerken-Van-Bocxlaer", "png", "image/png", "www.schilderwerken-vanbocxlaer.be/"));

    }

    private Sponsor constructSponsor(String name, String folder, String fileName, String extension, String contentType, String url) {
        String logo = fileName + "_logo." + extension;
        String website = fileName + "_website." + extension;

        Sponsor sponsor = new Sponsor();
        sponsor.setName(name);
        sponsor.setLogo(getByteArray(folder, logo, extension));
        sponsor.setLogoContentType(contentType);
        sponsor.setWebsite(url);
        sponsor.setWebsiteImage(getByteArray(folder, website, extension));
        sponsor.setWebsiteImageContentType(contentType);

        return sponsor;
    }

    private byte[] getByteArray(String folder, String fileName, String extension) {
         String path = "/BOOT-INF/classes/";
         String file = path + folder + "/" + fileName;
        
//        String dir = System.getProperty("user.dir");
//        String path = "/src/main/resources/";
//        String file = dir + path + folder + "/" + fileName;

        byte[] data = null;

        try {
            BufferedImage bImage = ImageIO.read(getClass().getResourceAsStream(file));
//            BufferedImage bImage = ImageIO.read(new File(file));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, extension, bos);
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private void loadEvents() {
//        Address address1 = new Address();
//        address1.setStreet("Dalialaan");
//        address1.setNumber("2A");
//        address1.setZipCode("9160");
//        address1.setCity("Lokeren");
//
//        Report report1 = new Report();
//        report1.setReport(getReport());
//
//        Event event1 = new Event();
//        Event event2 = new Event();
//
//        event1.setDate(LocalDate.of(2019,5,5));
//        event1.setTitle("Eetfestijn");
//        event1.setAddress(address1);
//        event1.setReport(report1);
//
//        event2.setDate(LocalDate.of(2019,5,10));
//        event2.setEndDate(LocalDate.of(2019,5,13));
//        event2.setTitle("Bike weekend");

//        eventRepository.save(event1);
//        eventRepository.save(event2);
    }

    private void loadTours() {
//        Tour tour1 = new Tour();
//        Tour tour2 = new Tour();

//        tour1.setDate(LocalDate.of(2019,5,19));
//        tour1.setTitle("tour 1");

//        tour2.setDate(LocalDate.of(2019,5,26));
//        tour2.setTitle("Tour 2");

//        tourRepository.save(tour1);
//        tourRepository.save(tour2);

        ActivityDTO dto1 = tourService.findExternalTours("2019-06-02").get(0);
        ActivityDTO dto2 = tourService.findExternalTours("2019-06-09").get(0);
        ActivityDTO dto3 = tourService.findExternalTours("2019-06-16").get(0);
        ActivityDTO dto4 = tourService.findExternalTours("2019-06-23").get(0);

        dto1.setType("tour");
        dto2.setType("tour");
        dto3.setType("tour");
        dto4.setType("tour");

        dto1.setDate(LocalDate.of(2019, 6, 2));
        dto2.setDate(LocalDate.of(2019, 6, 9));
        dto3.setDate(LocalDate.of(2019, 6, 16));
        dto4.setDate(LocalDate.of(2019, 6, 23));

        activityService.create(dto1);
        activityService.create(dto2);
        activityService.create(dto3);
        activityService.create(dto4);


    }

    private String getReport() {

        return "<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. " +
            "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
            "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
            "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
            "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo." +
            " Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus." +
            " Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, " +
            "porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, " +
            "feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. " +
            "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui.</p>";


    }

}
