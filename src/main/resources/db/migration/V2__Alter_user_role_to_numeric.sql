-- 1. Sử dụng lệnh ALTER TABLE kết hợp với USING để ép kiểu dữ liệu
-- Chúng ta mapping: 'CUSTOMER' -> 0, 'ADMIN' -> 1
-- Nếu có giá trị khác hoặc NULL, mặc định về 0 (CUSTOMER)

ALTER TABLE public.users 
ALTER COLUMN role TYPE SMALLINT 
USING (
    CASE 
        WHEN role = 'ADMIN' THEN 1
        ELSE 0 
    END
);

-- 2. Đảm bảo cột vẫn là NOT NULL (vì trong Entity bạn để nullable = false)
ALTER TABLE public.users ALTER COLUMN role SET NOT NULL;

-- 3. (Tùy chọn) Thêm một chú thích để sau này kiểm tra DB dễ hơn
COMMENT ON COLUMN public.users.role IS '0: CUSTOMER, 1: ADMIN';