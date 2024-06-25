CREATE OR REPLACE FUNCTION log_banking_message() RETURNS TRIGGER AS $$
DECLARE
transaction_message TEXT;
BEGIN
    transaction_message := 'Banking transaction of ' || NEW.amount || ' for account ' || NEW.account;
INSERT INTO Message (message, customer_id, created_time)
VALUES (transaction_message, NEW.customer_id, CURRENT_TIMESTAMP);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_saving_message() RETURNS TRIGGER AS $$
DECLARE
transaction_message TEXT;
BEGIN
    transaction_message := 'Saving transaction of ' || NEW.amount || ' for account ' || NEW.account;
INSERT INTO Message (message, customer_id, created_time)
VALUES (transaction_message, NEW.customer_id, CURRENT_TIMESTAMP);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_withdraw_message() RETURNS TRIGGER AS $$
DECLARE
transaction_message TEXT;
BEGIN
    transaction_message := 'Withdraw transaction of ' || NEW.amount || ' from account ' || NEW.account;
INSERT INTO Message (message, customer_id, created_time)
VALUES (transaction_message, NEW.customer_id, CURRENT_TIMESTAMP);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER after_banking_insert
    AFTER INSERT ON Banking
    FOR EACH ROW
    EXECUTE FUNCTION log_banking_message();

CREATE TRIGGER after_saving_insert
    AFTER INSERT ON Saving
    FOR EACH ROW
    EXECUTE FUNCTION log_saving_message();

CREATE TRIGGER after_withdraw_insert
    AFTER INSERT ON Withdraw
    FOR EACH ROW
    EXECUTE FUNCTION log_withdraw_message();
