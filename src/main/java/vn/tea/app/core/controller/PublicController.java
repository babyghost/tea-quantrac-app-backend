package vn.tea.app.core.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import vn.tea.app.config.CustomAuthenticationProvider;
import vn.tea.app.config.rest.JwtAuthenticationTokenFilter;
import vn.tea.app.core.dto.CoreUserData;
import vn.tea.app.core.dto.DataLogin;
import vn.tea.app.core.entity.CoreUser;
import vn.tea.app.core.service.CoreUserService;

@CrossOrigin
@Slf4j
@Controller
@RequestMapping(value = "/rest/public")
public class PublicController {
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private CoreUserBusiness coreUserBusiness;
	@Autowired
	private JwtAuthenticationTokenFilter jwtTokenProvider;
	@Autowired
	private CoreUserService coreUserService;
//	@Autowired
//	public SyncDataBusiness syncDataBusiness;
	
	private CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();

	@PostMapping(value = { "/login" })
	public ResponseEntity<?> login(@Valid @RequestBody DataLogin dataLogin, BindingResult result)
			throws MethodArgumentNotValidException {
//		Map<String, Object> reponse = new HashedMap<String, Object>();
 		log.info("Xác thực từ username và password: " + dataLogin);
		CoreUser coreUser = coreUserService.findFirstByUserNameAndDaXoa(dataLogin.getUserName(), 0);
		System.out.println(coreUser);
		String jwt = null;
		if(coreUser != null) {
			if(passwordEncoder.matches(dataLogin.getPassWord(), coreUser.getPassword())) {
				// Xác thực từ username và password.
				Authentication authentication = customAuthenticationProvider.authenticate(
						new UsernamePasswordAuthenticationToken(dataLogin.getUserName(), dataLogin.getPassWord()));
				// Nếu không xảy ra exception tức là thông tin hợp lệ
				// Set thông tin authentication vào Security Context
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.info("getPrincipal: "+ authentication.getPrincipal().toString());
				log.info("getCredentials: "+ authentication.getCredentials().toString());
				UserDetails userDetails =(User) authentication.getPrincipal();
				// Trả về jwt cho người dùng.
				jwt = jwtTokenProvider.generateToken(userDetails);
//				String jwt = jwtTokenProvider.generateTokenByUserName(authentication.getCredentials().toString());
				log.info("jwt: " + jwt);
			}
		}
//		reponse.put("Authorization", jwt);
		return ResponseEntity.status(HttpStatus.OK).body(jwt);
	}
	
	@GetMapping(value = { "/sso" })
	public ResponseEntity<?> loginSSO(@Valid @RequestBody Map<String, String> dataLogin, BindingResult result)
			throws MethodArgumentNotValidException {
		CoreUser coreUser = coreUserService.findFirstByUserNameAndDaXoa(dataLogin.get("userName"), 0);
		String jwt = null;
		if(coreUser != null) {
			if(passwordEncoder.matches(dataLogin.get("passWord"), coreUser.getPassword())) {
				// Xác thực từ username và password.
				Authentication authentication = customAuthenticationProvider.authenticate(
						new UsernamePasswordAuthenticationToken(dataLogin.get("userName"), dataLogin.get("passWord")));
				// Nếu không xảy ra exception tức là thông tin hợp lệ
				// Set thông tin authentication vào Security Context
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.info("getPrincipal: "+ authentication.getPrincipal().toString());
				log.info("getCredentials: "+ authentication.getCredentials().toString());
				UserDetails userDetails =(User) authentication.getPrincipal();
				// Trả về jwt cho người dùng.
				jwt = jwtTokenProvider.generateToken(userDetails);
				log.info("jwt: " + jwt);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(jwt);
	}
	
	@PostMapping(value = { "/register" })
	public ResponseEntity<CoreUser> create(@Valid @RequestBody CoreUserData coreUserData, BindingResult result)
			throws MethodArgumentNotValidException {
		CoreUser coreUser = coreUserBusiness.create(coreUserData, result);
		return ResponseEntity.status(HttpStatus.CREATED).body(coreUser);
	}
	
	@PostMapping(value = { "/convertToThumbnail" })
	public ResponseEntity<?> convert(@Valid @RequestBody Map<String, String> map, BindingResult result)
			throws MethodArgumentNotValidException {
		BufferedImage image;
		String base64Encoded = "";
		String base64 = map.get("base64");
		try {
			String data = base64.split(",")[1];
			image = ImageIO
					.read(new ByteArrayInputStream(Base64.getDecoder().decode(data)));
			log.info("width: {}  --- height: {}",image.getWidth(),  image.getHeight());
			
			Image tmp = image.getScaledInstance(image.getWidth()/3, image.getHeight()/3, Image.SCALE_SMOOTH);
		    BufferedImage resized = new BufferedImage(image.getWidth()/3, image.getHeight()/3, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = resized.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(resized, "PNG", baos);
			byte[] encodeBase64 =  Base64.getEncoder().encode(baos.toByteArray());
			base64Encoded = new String(encodeBase64);
			base64Encoded = base64.split(",")[0] + "," + base64Encoded;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(base64Encoded);
	}
	
	public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
	
//	@PostMapping(value = { "/synchand" })
//	public ResponseEntity<?> synchand() throws Exception {
//		Map<String, Object> resultDongBo = new HashedMap<String, Object>();
//		resultDongBo = syncDataBusiness.dongBoDuLieuCongTrinh(2);
//		return ResponseEntity.ok(resultDongBo);
//	}
//	
//	@PostMapping(value = { "/synchanddoanhnghiep" })
//	public ResponseEntity<?> synchanddoanhnghiep() throws Exception {
//		Map<String, Object> resultDongBo = new HashedMap<String, Object>();
//		syncDataBusiness.dongBoDuLieuDoanhNghiep();
//		resultDongBo.put("thanhcong", "1");
//		return ResponseEntity.ok(resultDongBo);
//	}
}
