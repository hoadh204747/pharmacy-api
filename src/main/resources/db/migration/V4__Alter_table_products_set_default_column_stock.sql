-- Bước 1: Đặt giá trị mặc định cho cột stock để các sản phẩm tạo mới sau này tự có số 10
ALTER TABLE public.products 
ALTER COLUMN stock SET DEFAULT 10;

-- Bước 2: Câu lệnh của bạn - Cập nhật dữ liệu cho các sản phẩm đã tồn tại
UPDATE public.products 
SET stock = 10 
WHERE stock IS NULL;

-- Bước 3: (Quan trọng) Khóa NOT NULL để đảm bảo sau này không ai vô tình để trống kho
ALTER TABLE public.products 
ALTER COLUMN stock SET NOT NULL;